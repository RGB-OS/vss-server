# VSS API Client

The VSS API Client is a Node.js utility designed to interact with a Versioned Storage Service (VSS). It provides functionality to securely store, retrieve, and manage encrypted data using a cloud-hosted VSS server.

---

## **Features**
- **File Storage**: Encrypt and upload files to the VSS server.
- **File Retrieval**: Decrypt and fetch files from the server.
- **Key Version Management**: List versions of stored keys for audit and recovery purposes.
- **Client-Side Encryption**: Ensures data is encrypted locally using AES-256-GCM before being transmitted.
- **Protobuf Serialization**: Leverages Protobuf for efficient data communication.

---

## **Getting Started**

### **Prerequisites**
1. **VSS Server**: Access to a running VSS server instance.
2. **Protobuf Schema**: Obtain the `vss.proto` file from the VSS service provider.

---

### **Installation**

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <repository-folder>
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Configure the client:
   - Update `config.js` with:
     - **`token`**: JWT token for authentication.
     - **`vssServerBaseUrl`**: Base URL for the VSS server.
     - **`ENCRYPTION_KEY`**: 32-byte AES encryption key in Base64 format.

---

## **Usage**

### **1. Save a File**
Upload a file to the VSS server:

```javascript
const { putObjects } = require('./client');

async function saveFile() {
    const filePath = 'example.txt';
    const storeId = 'store_example';
    const key = 'file_key';

    const response = await putObjects(filePath, storeId, key);
    console.log('File stored successfully:', response.data);
}

saveFile();
```

---

### **2. Retrieve a File**
Fetch and decrypt a file from the VSS server:

```javascript
const { getObject } = require('./client');

async function fetchFile() {
    const storeId = 'store_example';
    const key = 'file_key';

    const content = await getObject(storeId, key);
    console.log('Decrypted Content:', content);
}

fetchFile();
```

---

### **3. List Key Versions**
Retrieve a list of versions for a specific key:

```javascript
const { listKeyVersions } = require('./client');

async function listVersions() {
    const storeId = 'store_example';

    const versions = await listKeyVersions(storeId);
    console.log('Key Versions:', versions);
}

listVersions();
```

---

## **Configuration**

### **`config.js` Example**
```javascript
module.exports = {
    token: '<JWT_TOKEN>', // Replace with your JWT token
    vssServerBaseUrl: 'http://localhost:8080/vss', // Replace with your VSS server base URL
    ENCRYPTION_KEY: Buffer.from('<BASE64_ENCRYPTION_KEY>', 'base64') // Replace with a valid 32-byte encryption key
};
```

### **Generate an AES-256 Encryption Key**
Run the following command to generate a 32-byte key:
```bash
node -e "console.log(require('crypto').randomBytes(32).toString('base64'));"
```

---

## **Directory Structure**

```plaintext
.
├── client.js            # Main API client
├── encryptor.js         # Encryption and decryption utilities
├── protobufUtils.js     # Protobuf schema loader
├── config.js            # Configuration file
├── vss.proto            # Protobuf schema for VSS requests
└── README.md            # Documentation
```

---

## **Security**
- **Client-Side Encryption**: Data is encrypted locally using AES-256-GCM before being sent to the server.

---

## **Troubleshooting**

### **Common Issues**
1. **Invalid Token**:
   - Ensure the `token` in `config.js` is valid and not expired.
2. **Connection Errors**:
   - Verify the `vssServerBaseUrl` is correct and the server is running.
3. **Encryption Errors**:
   - Ensure the `ENCRYPTION_KEY` meets format and length requirements.

---

## **Contributing**

We welcome contributions! To contribute:
1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Submit a pull request with a clear description of changes.

---

## **Contact**
For questions or support, reach out to [contact@thunderstack.org](mailto:contact@thunderstack.org).
