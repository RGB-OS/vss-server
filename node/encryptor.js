const crypto = require('crypto');
const { ENCRYPTION_KEY } = require('./config');

/**
 * Encrypts the content using AES-256-GCM.
 * @param {string} content - The plaintext content to encrypt.
 * @returns {Object} - Encrypted data, nonce, and authentication tag.
 */
function encryptContent(content) {
    const nonce = crypto.randomBytes(12); // Generate a 12-byte nonce
    const cipher = crypto.createCipheriv('aes-256-gcm', ENCRYPTION_KEY, nonce);

    // Encrypt the content
    let encrypted = cipher.update(content, 'utf8');
    encrypted = Buffer.concat([encrypted, cipher.final()]);

    // Get the authentication tag
    const tag = cipher.getAuthTag();

    return {
        encryptedData: encrypted.toString('base64'),
        nonce: nonce.toString('base64'),
        tag: tag.toString('base64')
    };
}

/**
 * Decrypts the content using AES-256-GCM.
 * @param {string} encryptedContent - The Base64-encoded encrypted content.
 * @param {string} nonce - The Base64-encoded nonce.
 * @param {string} tag - The Base64-encoded authentication tag.
 * @returns {string} - Decrypted plaintext content.
 */
function decryptContent(encryptedContent, nonce, tag) {
    const decipher = crypto.createDecipheriv('aes-256-gcm', ENCRYPTION_KEY, Buffer.from(nonce, 'base64'));
    decipher.setAuthTag(Buffer.from(tag, 'base64')); // Set the authentication tag

    // Decrypt the content
    let decrypted = decipher.update(encryptedContent, 'base64', 'utf8');
    decrypted += decipher.final('utf8');

    return decrypted;
}

module.exports = { encryptContent, decryptContent };