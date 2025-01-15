const axios = require('axios');
const fs = require('fs');
const { encryptContent, decryptContent } = require('./encryptor');
const { loadProto } = require('./protobufUtils');
const { token, vssServerBaseUrl } = require('./config');

/**
 * Save a file to the VSS server.
 * @param {string} filePath - Path to the file to be saved.
 * @param {string} storeId - The store ID for the operation.
 * @param {string} key - The key under which the file is stored.
 */
async function putObjects(filePath, storeId, key, version = 0) {
    const root = await loadProto();
    const PutObjectRequest = root.lookupType('PutObjectRequest');
    const KeyValue = root.lookupType('KeyValue');
    const Storable = root.lookupType('Storable');
    const EncryptionMetadata = root.lookupType('EncryptionMetadata');

    // Read the file and encrypt its content
    const fileContent = fs.readFileSync(filePath, 'utf8');
    const encryptedValue = encryptContent(fileContent);

    // Prepare the protobuf objects
    const encryptionMetadata = EncryptionMetadata.create({
        cipherFormat: 'AES-256-GCM',
        nonce: Buffer.from(encryptedValue.nonce, 'base64'),
        tag: Buffer.from(encryptedValue.tag, 'base64')
    });

    const storable = Storable.create({
        data: Buffer.from(encryptedValue.encryptedData, 'base64'),
        encryptionMetadata: encryptionMetadata
    });

    const keyValue = KeyValue.create({
        key: key,
        value: Storable.encode(storable).finish(),
        version: version
    });

    const message = PutObjectRequest.create({
        storeId: storeId,
        globalVersion: version,
        transactionItems: [keyValue]
    });

    const binaryRequest = PutObjectRequest.encode(message).finish();

    // Send the request to the VSS server
    const response = await axios.post(
        `${vssServerBaseUrl}/putObjects`,
        binaryRequest,
        {
            headers: { 'Authorization': `Bearer ${token}`, 'Content-Type': 'application/octet-stream' },
            responseType: 'arraybuffer'
        }
    );
    return response;
}

/**
 * Fetch a file from the VSS server and decrypt it.
 * @param {string} storeId - The store ID for the operation.
 * @param {string} key - The key under which the file is stored.
 * @returns {string} - Decrypted file content.
 */
async function getObject(storeId, key) {
    const root = await loadProto();
    const GetObjectRequest = root.lookupType('GetObjectRequest');
    const GetObjectResponse = root.lookupType('GetObjectResponse');
    const Storable = root.lookupType('Storable');

    const request = GetObjectRequest.create({ storeId, key });
    const binaryRequest = GetObjectRequest.encode(request).finish();

    const response = await axios.post(
        `${vssServerBaseUrl}/getObject`,
        binaryRequest,
        {
            headers: { 'Authorization': `Bearer ${token}`, 'Content-Type': 'application/octet-stream' },
            responseType: 'arraybuffer'
        }
    );

    const decodedResponse = GetObjectResponse.decode(new Uint8Array(response.data));
    const storable = Storable.decode(decodedResponse.value.value);

    return decryptContent(storable.data, storable.encryptionMetadata.nonce, storable.encryptionMetadata.tag);
}

/**
 * List key versions for a given store.
 * @param {string} storeId - The store ID for the operation.
 */
async function listKeyVersions(storeId, key = null) {
    const root = await loadProto();
    const ListKeyVersionsRequest = root.lookupType('ListKeyVersionsRequest');

    const request = ListKeyVersionsRequest.create({ storeId, key });
    const binaryRequest = ListKeyVersionsRequest.encode(request).finish();

    const response = await axios.post(
        `${vssServerBaseUrl}/listKeyVersions`,
        binaryRequest,
        {
            headers: { 'Authorization': `Bearer ${token}`, 'Content-Type': 'application/octet-stream' },
            responseType: 'arraybuffer'
        }
    );

    const ListKeyVersionsResponse = root.lookupType('ListKeyVersionsResponse');
    const decodedResponse = ListKeyVersionsResponse.decode(new Uint8Array(response.data));
    return decodedResponse
}

module.exports = { putObjects, getObject, listKeyVersions };
