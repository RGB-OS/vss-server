const { putObjects, getObject, listKeyVersions } = require('./client');

async function main() {
    try {
        const timestamp = Date.now();
        const storeId = `store_ex_${timestamp}`;
        const key = `k_ex_${timestamp}`;

        console.log('Saving file...');
        const response = await putObjects('example.txt', storeId, key);
        console.log('Succesfully stored:', response.data);

        console.log('Listing key versions...');
        const keyVersions = await listKeyVersions(storeId);
        console.log('Key Versions:', keyVersions);

        console.log('Fetching and decrypting file...');
        const content = await getObject(storeId, key);
        console.log('Decrypted Content:', content);

    } catch (error) {
        console.error('Error:', error.message);
    }
}

main();
