const protobuf = require('protobufjs');

/**
 * Loads and parses the protobuf schema.
 * @returns {Promise<protobuf.Root>} - The parsed protobuf root object.
 */
async function loadProto() {
    return await protobuf.load('vss.proto');
}

module.exports = { loadProto };