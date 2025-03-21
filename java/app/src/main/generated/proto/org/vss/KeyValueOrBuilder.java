// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: vss.proto

// Protobuf Java Version: 3.25.5
package org.vss;

public interface KeyValueOrBuilder extends
    // @@protoc_insertion_point(interface_extends:vss.KeyValue)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Key against which the value is stored.
   * </pre>
   *
   * <code>string key = 1;</code>
   * @return The key.
   */
  java.lang.String getKey();
  /**
   * <pre>
   * Key against which the value is stored.
   * </pre>
   *
   * <code>string key = 1;</code>
   * @return The bytes for key.
   */
  com.google.protobuf.ByteString
      getKeyBytes();

  /**
   * <pre>
   * Version field is used for key-level versioning.
   * For first write of key, `version` should be '0'. If the write succeeds, clients must increment
   * their corresponding key version (client-side) by 1.
   * The server increments key version (server-side) for every successful write, hence this
   * client-side increment is required to ensure matching versions. These updated key versions should
   * be used in subsequent `PutObjectRequest`s for the keys.
   * </pre>
   *
   * <code>int64 version = 2;</code>
   * @return The version.
   */
  long getVersion();

  /**
   * <pre>
   * Object value in bytes which is stored (in put) and fetched (in get).
   * Clients must encrypt the secret contents of this blob client-side before sending it over the
   * wire to the server in order to preserve privacy and security.
   * Clients may use a `Storable` object, serialize it and set it here.
   * </pre>
   *
   * <code>bytes value = 3;</code>
   * @return The value.
   */
  com.google.protobuf.ByteString getValue();
}
