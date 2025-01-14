// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: vss.proto

// Protobuf Java Version: 3.25.5
package org.vss;

public interface PutObjectRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:vss.PutObjectRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * `store_id` is a keyspace identifier.
   * Ref: https://en.wikipedia.org/wiki/Keyspace_(distributed_data_store)
   * All APIs operate within a single `store_id`.
   * It is up to clients to use single or multiple stores for their use-case.
   * This can be used for client-isolation/ rate-limiting / throttling on the server-side.
   * Authorization and billing can also be performed at the `store_id` level.
   * </pre>
   *
   * <code>string store_id = 1;</code>
   * @return The storeId.
   */
  java.lang.String getStoreId();
  /**
   * <pre>
   * `store_id` is a keyspace identifier.
   * Ref: https://en.wikipedia.org/wiki/Keyspace_(distributed_data_store)
   * All APIs operate within a single `store_id`.
   * It is up to clients to use single or multiple stores for their use-case.
   * This can be used for client-isolation/ rate-limiting / throttling on the server-side.
   * Authorization and billing can also be performed at the `store_id` level.
   * </pre>
   *
   * <code>string store_id = 1;</code>
   * @return The bytes for storeId.
   */
  com.google.protobuf.ByteString
      getStoreIdBytes();

  /**
   * <pre>
   * `global_version` is a sequence-number/version of the whole store. This can be used for versioning
   * and ensures that multiple updates in case of multiple devices can only be done linearly, even
   * if those updates did not directly conflict with each other based on keys/`transaction_items`.
   *
   * If present, the write will only succeed if the current server-side `global_version` against
   * the `store_id` is same as in the request.
   * Clients are expected to store (client-side) the global version against `store_id`.
   * The request must contain their client-side value of `global_version` if global versioning and
   * conflict detection is desired.
   *
   * For the first write of the store, global version should be '0'. If the write succeeds, clients
   * must increment their global version (client-side) by 1.
   * The server increments `global_version` (server-side) for every successful write, hence this
   * client-side increment is required to ensure matching versions. This updated global version
   * should be used in subsequent `PutObjectRequest`s for the store.
   *
   * Requests with a conflicting version will fail with `CONFLICT_EXCEPTION` as ErrorCode.
   * </pre>
   *
   * <code>optional int64 global_version = 2;</code>
   * @return Whether the globalVersion field is set.
   */
  boolean hasGlobalVersion();
  /**
   * <pre>
   * `global_version` is a sequence-number/version of the whole store. This can be used for versioning
   * and ensures that multiple updates in case of multiple devices can only be done linearly, even
   * if those updates did not directly conflict with each other based on keys/`transaction_items`.
   *
   * If present, the write will only succeed if the current server-side `global_version` against
   * the `store_id` is same as in the request.
   * Clients are expected to store (client-side) the global version against `store_id`.
   * The request must contain their client-side value of `global_version` if global versioning and
   * conflict detection is desired.
   *
   * For the first write of the store, global version should be '0'. If the write succeeds, clients
   * must increment their global version (client-side) by 1.
   * The server increments `global_version` (server-side) for every successful write, hence this
   * client-side increment is required to ensure matching versions. This updated global version
   * should be used in subsequent `PutObjectRequest`s for the store.
   *
   * Requests with a conflicting version will fail with `CONFLICT_EXCEPTION` as ErrorCode.
   * </pre>
   *
   * <code>optional int64 global_version = 2;</code>
   * @return The globalVersion.
   */
  long getGlobalVersion();

  /**
   * <pre>
   * Items to be written as a result of this `PutObjectRequest`.
   *
   * In an item, each `key` is supplied with its corresponding `value` and `version`.
   * Clients can choose to encrypt the keys client-side in order to obfuscate their usage patterns.
   * If the write is successful, the previous `value` corresponding to the `key` will be overwritten.
   *
   * Multiple items in `transaction_items` and `delete_items` of a single `PutObjectRequest` are written in
   * a database-transaction in an all-or-nothing fashion.
   * All Items in a single `PutObjectRequest` must have distinct keys.
   *
   * Key-level versioning (Conditional Write):
   *   Clients are expected to store a `version` against every `key`.
   *   The write will succeed if the current DB version against the `key` is the same as in the request.
   *   When initiating a `PutObjectRequest`, the request should contain their client-side `version`
   *   for that key-value.
   *
   *   For the first write of any `key`, the `version` should be '0'. If the write succeeds, the client
   *   must increment their corresponding key versions (client-side) by 1.
   *   The server increments key versions (server-side) for every successful write, hence this
   *   client-side increment is required to ensure matching versions. These updated key versions should
   *   be used in subsequent `PutObjectRequest`s for the keys.
   *
   *   Requests with a conflicting/mismatched version will fail with `CONFLICT_EXCEPTION` as ErrorCode
   *   for conditional writes.
   *
   * Skipping key-level versioning (Non-conditional Write):
   *   If you wish to skip key-level version checks, set the `version` against the `key` to '-1'.
   *   This will perform a non-conditional write query, after which the `version` against the `key`
   *   is reset to '1'. Hence, the next `PutObjectRequest` for the `key` can be either
   *   a non-conditional write or a conditional write with `version` set to `1`.
   *
   * Considerations for transactions:
   * Transaction writes of multiple items have a performance overhead, hence it is recommended to use
   * them only if required by the client application to ensure logic/code correctness.
   * That is, `transaction_items` are not a substitute for batch-write of multiple unrelated items.
   * When a write of multiple unrelated items is desired, it is recommended to use separate
   * `PutObjectRequest`s.
   *
   * Consistency guarantee:
   * All `PutObjectRequest`s are strongly consistent i.e. they provide read-after-write and
   * read-after-update consistency guarantees.
   * </pre>
   *
   * <code>repeated .vss.KeyValue transaction_items = 3;</code>
   */
  java.util.List<org.vss.KeyValue> 
      getTransactionItemsList();
  /**
   * <pre>
   * Items to be written as a result of this `PutObjectRequest`.
   *
   * In an item, each `key` is supplied with its corresponding `value` and `version`.
   * Clients can choose to encrypt the keys client-side in order to obfuscate their usage patterns.
   * If the write is successful, the previous `value` corresponding to the `key` will be overwritten.
   *
   * Multiple items in `transaction_items` and `delete_items` of a single `PutObjectRequest` are written in
   * a database-transaction in an all-or-nothing fashion.
   * All Items in a single `PutObjectRequest` must have distinct keys.
   *
   * Key-level versioning (Conditional Write):
   *   Clients are expected to store a `version` against every `key`.
   *   The write will succeed if the current DB version against the `key` is the same as in the request.
   *   When initiating a `PutObjectRequest`, the request should contain their client-side `version`
   *   for that key-value.
   *
   *   For the first write of any `key`, the `version` should be '0'. If the write succeeds, the client
   *   must increment their corresponding key versions (client-side) by 1.
   *   The server increments key versions (server-side) for every successful write, hence this
   *   client-side increment is required to ensure matching versions. These updated key versions should
   *   be used in subsequent `PutObjectRequest`s for the keys.
   *
   *   Requests with a conflicting/mismatched version will fail with `CONFLICT_EXCEPTION` as ErrorCode
   *   for conditional writes.
   *
   * Skipping key-level versioning (Non-conditional Write):
   *   If you wish to skip key-level version checks, set the `version` against the `key` to '-1'.
   *   This will perform a non-conditional write query, after which the `version` against the `key`
   *   is reset to '1'. Hence, the next `PutObjectRequest` for the `key` can be either
   *   a non-conditional write or a conditional write with `version` set to `1`.
   *
   * Considerations for transactions:
   * Transaction writes of multiple items have a performance overhead, hence it is recommended to use
   * them only if required by the client application to ensure logic/code correctness.
   * That is, `transaction_items` are not a substitute for batch-write of multiple unrelated items.
   * When a write of multiple unrelated items is desired, it is recommended to use separate
   * `PutObjectRequest`s.
   *
   * Consistency guarantee:
   * All `PutObjectRequest`s are strongly consistent i.e. they provide read-after-write and
   * read-after-update consistency guarantees.
   * </pre>
   *
   * <code>repeated .vss.KeyValue transaction_items = 3;</code>
   */
  org.vss.KeyValue getTransactionItems(int index);
  /**
   * <pre>
   * Items to be written as a result of this `PutObjectRequest`.
   *
   * In an item, each `key` is supplied with its corresponding `value` and `version`.
   * Clients can choose to encrypt the keys client-side in order to obfuscate their usage patterns.
   * If the write is successful, the previous `value` corresponding to the `key` will be overwritten.
   *
   * Multiple items in `transaction_items` and `delete_items` of a single `PutObjectRequest` are written in
   * a database-transaction in an all-or-nothing fashion.
   * All Items in a single `PutObjectRequest` must have distinct keys.
   *
   * Key-level versioning (Conditional Write):
   *   Clients are expected to store a `version` against every `key`.
   *   The write will succeed if the current DB version against the `key` is the same as in the request.
   *   When initiating a `PutObjectRequest`, the request should contain their client-side `version`
   *   for that key-value.
   *
   *   For the first write of any `key`, the `version` should be '0'. If the write succeeds, the client
   *   must increment their corresponding key versions (client-side) by 1.
   *   The server increments key versions (server-side) for every successful write, hence this
   *   client-side increment is required to ensure matching versions. These updated key versions should
   *   be used in subsequent `PutObjectRequest`s for the keys.
   *
   *   Requests with a conflicting/mismatched version will fail with `CONFLICT_EXCEPTION` as ErrorCode
   *   for conditional writes.
   *
   * Skipping key-level versioning (Non-conditional Write):
   *   If you wish to skip key-level version checks, set the `version` against the `key` to '-1'.
   *   This will perform a non-conditional write query, after which the `version` against the `key`
   *   is reset to '1'. Hence, the next `PutObjectRequest` for the `key` can be either
   *   a non-conditional write or a conditional write with `version` set to `1`.
   *
   * Considerations for transactions:
   * Transaction writes of multiple items have a performance overhead, hence it is recommended to use
   * them only if required by the client application to ensure logic/code correctness.
   * That is, `transaction_items` are not a substitute for batch-write of multiple unrelated items.
   * When a write of multiple unrelated items is desired, it is recommended to use separate
   * `PutObjectRequest`s.
   *
   * Consistency guarantee:
   * All `PutObjectRequest`s are strongly consistent i.e. they provide read-after-write and
   * read-after-update consistency guarantees.
   * </pre>
   *
   * <code>repeated .vss.KeyValue transaction_items = 3;</code>
   */
  int getTransactionItemsCount();
  /**
   * <pre>
   * Items to be written as a result of this `PutObjectRequest`.
   *
   * In an item, each `key` is supplied with its corresponding `value` and `version`.
   * Clients can choose to encrypt the keys client-side in order to obfuscate their usage patterns.
   * If the write is successful, the previous `value` corresponding to the `key` will be overwritten.
   *
   * Multiple items in `transaction_items` and `delete_items` of a single `PutObjectRequest` are written in
   * a database-transaction in an all-or-nothing fashion.
   * All Items in a single `PutObjectRequest` must have distinct keys.
   *
   * Key-level versioning (Conditional Write):
   *   Clients are expected to store a `version` against every `key`.
   *   The write will succeed if the current DB version against the `key` is the same as in the request.
   *   When initiating a `PutObjectRequest`, the request should contain their client-side `version`
   *   for that key-value.
   *
   *   For the first write of any `key`, the `version` should be '0'. If the write succeeds, the client
   *   must increment their corresponding key versions (client-side) by 1.
   *   The server increments key versions (server-side) for every successful write, hence this
   *   client-side increment is required to ensure matching versions. These updated key versions should
   *   be used in subsequent `PutObjectRequest`s for the keys.
   *
   *   Requests with a conflicting/mismatched version will fail with `CONFLICT_EXCEPTION` as ErrorCode
   *   for conditional writes.
   *
   * Skipping key-level versioning (Non-conditional Write):
   *   If you wish to skip key-level version checks, set the `version` against the `key` to '-1'.
   *   This will perform a non-conditional write query, after which the `version` against the `key`
   *   is reset to '1'. Hence, the next `PutObjectRequest` for the `key` can be either
   *   a non-conditional write or a conditional write with `version` set to `1`.
   *
   * Considerations for transactions:
   * Transaction writes of multiple items have a performance overhead, hence it is recommended to use
   * them only if required by the client application to ensure logic/code correctness.
   * That is, `transaction_items` are not a substitute for batch-write of multiple unrelated items.
   * When a write of multiple unrelated items is desired, it is recommended to use separate
   * `PutObjectRequest`s.
   *
   * Consistency guarantee:
   * All `PutObjectRequest`s are strongly consistent i.e. they provide read-after-write and
   * read-after-update consistency guarantees.
   * </pre>
   *
   * <code>repeated .vss.KeyValue transaction_items = 3;</code>
   */
  java.util.List<? extends org.vss.KeyValueOrBuilder> 
      getTransactionItemsOrBuilderList();
  /**
   * <pre>
   * Items to be written as a result of this `PutObjectRequest`.
   *
   * In an item, each `key` is supplied with its corresponding `value` and `version`.
   * Clients can choose to encrypt the keys client-side in order to obfuscate their usage patterns.
   * If the write is successful, the previous `value` corresponding to the `key` will be overwritten.
   *
   * Multiple items in `transaction_items` and `delete_items` of a single `PutObjectRequest` are written in
   * a database-transaction in an all-or-nothing fashion.
   * All Items in a single `PutObjectRequest` must have distinct keys.
   *
   * Key-level versioning (Conditional Write):
   *   Clients are expected to store a `version` against every `key`.
   *   The write will succeed if the current DB version against the `key` is the same as in the request.
   *   When initiating a `PutObjectRequest`, the request should contain their client-side `version`
   *   for that key-value.
   *
   *   For the first write of any `key`, the `version` should be '0'. If the write succeeds, the client
   *   must increment their corresponding key versions (client-side) by 1.
   *   The server increments key versions (server-side) for every successful write, hence this
   *   client-side increment is required to ensure matching versions. These updated key versions should
   *   be used in subsequent `PutObjectRequest`s for the keys.
   *
   *   Requests with a conflicting/mismatched version will fail with `CONFLICT_EXCEPTION` as ErrorCode
   *   for conditional writes.
   *
   * Skipping key-level versioning (Non-conditional Write):
   *   If you wish to skip key-level version checks, set the `version` against the `key` to '-1'.
   *   This will perform a non-conditional write query, after which the `version` against the `key`
   *   is reset to '1'. Hence, the next `PutObjectRequest` for the `key` can be either
   *   a non-conditional write or a conditional write with `version` set to `1`.
   *
   * Considerations for transactions:
   * Transaction writes of multiple items have a performance overhead, hence it is recommended to use
   * them only if required by the client application to ensure logic/code correctness.
   * That is, `transaction_items` are not a substitute for batch-write of multiple unrelated items.
   * When a write of multiple unrelated items is desired, it is recommended to use separate
   * `PutObjectRequest`s.
   *
   * Consistency guarantee:
   * All `PutObjectRequest`s are strongly consistent i.e. they provide read-after-write and
   * read-after-update consistency guarantees.
   * </pre>
   *
   * <code>repeated .vss.KeyValue transaction_items = 3;</code>
   */
  org.vss.KeyValueOrBuilder getTransactionItemsOrBuilder(
      int index);

  /**
   * <pre>
   * Items to be deleted as a result of this `PutObjectRequest`.
   *
   * Each item in the `delete_items` field consists of a `key` and its corresponding `version`.
   *
   * Key-Level Versioning (Conditional Delete):
   *   The `version` is used to perform a version check before deleting the item.
   *   The delete will only succeed if the current database version against the `key` is the same as
   *   the `version` specified in the request.
   *
   * Skipping key-level versioning (Non-conditional Delete):
   *   If you wish to skip key-level version checks, set the `version` against the `key` to '-1'.
   *   This will perform a non-conditional delete query.
   *
   * Fails with `CONFLICT_EXCEPTION` as the ErrorCode if:
   *   * The requested item does not exist.
   *   * The requested item does exist but there is a version-number mismatch (in conditional delete)
   *     with the one in the database.
   *
   * Multiple items in the `delete_items` field, along with the `transaction_items`, are written in a
   * database transaction in an all-or-nothing fashion.
   *
   * All items within a single `PutObjectRequest` must have distinct keys.
   * </pre>
   *
   * <code>repeated .vss.KeyValue delete_items = 4;</code>
   */
  java.util.List<org.vss.KeyValue> 
      getDeleteItemsList();
  /**
   * <pre>
   * Items to be deleted as a result of this `PutObjectRequest`.
   *
   * Each item in the `delete_items` field consists of a `key` and its corresponding `version`.
   *
   * Key-Level Versioning (Conditional Delete):
   *   The `version` is used to perform a version check before deleting the item.
   *   The delete will only succeed if the current database version against the `key` is the same as
   *   the `version` specified in the request.
   *
   * Skipping key-level versioning (Non-conditional Delete):
   *   If you wish to skip key-level version checks, set the `version` against the `key` to '-1'.
   *   This will perform a non-conditional delete query.
   *
   * Fails with `CONFLICT_EXCEPTION` as the ErrorCode if:
   *   * The requested item does not exist.
   *   * The requested item does exist but there is a version-number mismatch (in conditional delete)
   *     with the one in the database.
   *
   * Multiple items in the `delete_items` field, along with the `transaction_items`, are written in a
   * database transaction in an all-or-nothing fashion.
   *
   * All items within a single `PutObjectRequest` must have distinct keys.
   * </pre>
   *
   * <code>repeated .vss.KeyValue delete_items = 4;</code>
   */
  org.vss.KeyValue getDeleteItems(int index);
  /**
   * <pre>
   * Items to be deleted as a result of this `PutObjectRequest`.
   *
   * Each item in the `delete_items` field consists of a `key` and its corresponding `version`.
   *
   * Key-Level Versioning (Conditional Delete):
   *   The `version` is used to perform a version check before deleting the item.
   *   The delete will only succeed if the current database version against the `key` is the same as
   *   the `version` specified in the request.
   *
   * Skipping key-level versioning (Non-conditional Delete):
   *   If you wish to skip key-level version checks, set the `version` against the `key` to '-1'.
   *   This will perform a non-conditional delete query.
   *
   * Fails with `CONFLICT_EXCEPTION` as the ErrorCode if:
   *   * The requested item does not exist.
   *   * The requested item does exist but there is a version-number mismatch (in conditional delete)
   *     with the one in the database.
   *
   * Multiple items in the `delete_items` field, along with the `transaction_items`, are written in a
   * database transaction in an all-or-nothing fashion.
   *
   * All items within a single `PutObjectRequest` must have distinct keys.
   * </pre>
   *
   * <code>repeated .vss.KeyValue delete_items = 4;</code>
   */
  int getDeleteItemsCount();
  /**
   * <pre>
   * Items to be deleted as a result of this `PutObjectRequest`.
   *
   * Each item in the `delete_items` field consists of a `key` and its corresponding `version`.
   *
   * Key-Level Versioning (Conditional Delete):
   *   The `version` is used to perform a version check before deleting the item.
   *   The delete will only succeed if the current database version against the `key` is the same as
   *   the `version` specified in the request.
   *
   * Skipping key-level versioning (Non-conditional Delete):
   *   If you wish to skip key-level version checks, set the `version` against the `key` to '-1'.
   *   This will perform a non-conditional delete query.
   *
   * Fails with `CONFLICT_EXCEPTION` as the ErrorCode if:
   *   * The requested item does not exist.
   *   * The requested item does exist but there is a version-number mismatch (in conditional delete)
   *     with the one in the database.
   *
   * Multiple items in the `delete_items` field, along with the `transaction_items`, are written in a
   * database transaction in an all-or-nothing fashion.
   *
   * All items within a single `PutObjectRequest` must have distinct keys.
   * </pre>
   *
   * <code>repeated .vss.KeyValue delete_items = 4;</code>
   */
  java.util.List<? extends org.vss.KeyValueOrBuilder> 
      getDeleteItemsOrBuilderList();
  /**
   * <pre>
   * Items to be deleted as a result of this `PutObjectRequest`.
   *
   * Each item in the `delete_items` field consists of a `key` and its corresponding `version`.
   *
   * Key-Level Versioning (Conditional Delete):
   *   The `version` is used to perform a version check before deleting the item.
   *   The delete will only succeed if the current database version against the `key` is the same as
   *   the `version` specified in the request.
   *
   * Skipping key-level versioning (Non-conditional Delete):
   *   If you wish to skip key-level version checks, set the `version` against the `key` to '-1'.
   *   This will perform a non-conditional delete query.
   *
   * Fails with `CONFLICT_EXCEPTION` as the ErrorCode if:
   *   * The requested item does not exist.
   *   * The requested item does exist but there is a version-number mismatch (in conditional delete)
   *     with the one in the database.
   *
   * Multiple items in the `delete_items` field, along with the `transaction_items`, are written in a
   * database transaction in an all-or-nothing fashion.
   *
   * All items within a single `PutObjectRequest` must have distinct keys.
   * </pre>
   *
   * <code>repeated .vss.KeyValue delete_items = 4;</code>
   */
  org.vss.KeyValueOrBuilder getDeleteItemsOrBuilder(
      int index);
}
