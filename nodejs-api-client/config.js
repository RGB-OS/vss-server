/**
 * Configuration for the VSS API Client
 */
module.exports = {
    // JWT Token for authentication. Obtain this from the ThunderStack Cloud.
    token: 'eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI0MTViODVlMC1hMDcxLTcwZmQtNDRkZi0yMmI5MTFlYWEwMzQiLCJqdGkiOiJhMzM3Nzc0Ni04ZmQ4LTRiMDAtODJlNy1mMTMxM2RiN2ZjM2YiLCJpYXQiOjE3MjE5ODIzNTB9.QSt3Lkfi987Cf6Ob0c2RYa83KLFGtTlwdCgEStwYDJ22pEDZMJ3K4rlk7DsLKYmztIY1XbJHQ2qcYNHIpX27Ba8FspcBKCY_hzUKz9ZGWvkY2mOGrU7K4s56zg79FKUYWQYd_ccy01uEIS0JbVQgcDKFjchiDYmiIxo4CQAg9bgfqYhTXRhHVrzSLpYovqWw943JkY6jZxQp04cTPfJ_0I6fqyTbaEC47wFFycIgpxbtIE1xl1cOgjKU1EoHtP3T6ulJQ0TxV7k7N3VbYCTxJRqJ7Xkr57nZWGzH2NzB2uDn4AlGJWEYNh7PWlF8oPLbNjBo8oHux4QHt-ZdgSktZQ',
    // Base URL for the VSS server
    vssServerBaseUrl: 'http://localhost:8080/vss',
    // Encryption key for AES-256-GCM encryption. Generate it using the method below.
    ENCRYPTION_KEY: Buffer.from('QPrNNBh2zMKN7qZko298plRzbDxGoIhwktqo7sD6HIc=', 'base64')
};