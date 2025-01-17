/*
 * This file is generated by jOOQ.
 */
package org.vss.postgres.tables.pojos;


import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Arrays;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VssDb implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String userToken;
    private final String storeId;
    private final String key;
    private final byte[] value;
    private final Long version;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime lastUpdatedAt;

    public VssDb(VssDb value) {
        this.userToken = value.userToken;
        this.storeId = value.storeId;
        this.key = value.key;
        this.value = value.value;
        this.version = value.version;
        this.createdAt = value.createdAt;
        this.lastUpdatedAt = value.lastUpdatedAt;
    }

    public VssDb(
        String userToken,
        String storeId,
        String key,
        byte[] value,
        Long version,
        OffsetDateTime createdAt,
        OffsetDateTime lastUpdatedAt
    ) {
        this.userToken = userToken;
        this.storeId = storeId;
        this.key = key;
        this.value = value;
        this.version = version;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    /**
     * Getter for <code>public.vss_db.user_token</code>.
     */
    public String getUserToken() {
        return this.userToken;
    }

    /**
     * Getter for <code>public.vss_db.store_id</code>.
     */
    public String getStoreId() {
        return this.storeId;
    }

    /**
     * Getter for <code>public.vss_db.key</code>.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Getter for <code>public.vss_db.value</code>.
     */
    public byte[] getValue() {
        return this.value;
    }

    /**
     * Getter for <code>public.vss_db.version</code>.
     */
    public Long getVersion() {
        return this.version;
    }

    /**
     * Getter for <code>public.vss_db.created_at</code>.
     */
    public OffsetDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Getter for <code>public.vss_db.last_updated_at</code>.
     */
    public OffsetDateTime getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final VssDb other = (VssDb) obj;
        if (this.userToken == null) {
            if (other.userToken != null)
                return false;
        }
        else if (!this.userToken.equals(other.userToken))
            return false;
        if (this.storeId == null) {
            if (other.storeId != null)
                return false;
        }
        else if (!this.storeId.equals(other.storeId))
            return false;
        if (this.key == null) {
            if (other.key != null)
                return false;
        }
        else if (!this.key.equals(other.key))
            return false;
        if (this.value == null) {
            if (other.value != null)
                return false;
        }
        else if (!Arrays.equals(this.value, other.value))
            return false;
        if (this.version == null) {
            if (other.version != null)
                return false;
        }
        else if (!this.version.equals(other.version))
            return false;
        if (this.createdAt == null) {
            if (other.createdAt != null)
                return false;
        }
        else if (!this.createdAt.equals(other.createdAt))
            return false;
        if (this.lastUpdatedAt == null) {
            if (other.lastUpdatedAt != null)
                return false;
        }
        else if (!this.lastUpdatedAt.equals(other.lastUpdatedAt))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.userToken == null) ? 0 : this.userToken.hashCode());
        result = prime * result + ((this.storeId == null) ? 0 : this.storeId.hashCode());
        result = prime * result + ((this.key == null) ? 0 : this.key.hashCode());
        result = prime * result + ((this.value == null) ? 0 : Arrays.hashCode(this.value));
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.lastUpdatedAt == null) ? 0 : this.lastUpdatedAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("VssDb (");

        sb.append(userToken);
        sb.append(", ").append(storeId);
        sb.append(", ").append(key);
        sb.append(", ").append("[binary...]");
        sb.append(", ").append(version);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(lastUpdatedAt);

        sb.append(")");
        return sb.toString();
    }
}
