package org.landal.bookland.model;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

import com.google.common.base.Preconditions;

public class Attachment implements Identifiable<String>, Timestampable, Serializable {
    
    private static final long serialVersionUID = 1L;

    private final String id;

    private final String title;

    private final String mimeType;

    private final URL url;

    private final Date created;

    private final Date updated;

    public Attachment(String title, String mimeType, URL url) {
        this(UUID.randomUUID().toString(), title, mimeType, url, new Date());
    }

    private Attachment(String id, String title, String mimeType, URL url, Date created) {
        Preconditions.checkNotNull(title, "Title must be specified");
        Preconditions.checkNotNull(mimeType, "MimeType must be specified");
        Preconditions.checkNotNull(url, "Url must be specified");
        this.id = id;
        this.created = created;
        this.updated = new Date();
        this.title = title;
        this.mimeType = mimeType;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Attachment setTitle(String title) {
        return new Attachment(this.id, title, this.mimeType, this.url, this.created);
    }

    public String getMimeType() {
        return mimeType;
    }

    public Attachment setMimeType(String mimeType) {
        return new Attachment(this.id, this.title, mimeType, this.url, this.created);
    }

    public URL getUrl() {
        return url;
    }

    public Attachment setUrl(URL url) {
        return new Attachment(this.id, this.title, this.mimeType, url, this.created);
    }

    public Date getLastUpdated() {
        return updated == null ? null:(Date)updated.clone();
    }

    @Override
    public Date getCreated() {
        return created == null ? null:(Date)created.clone();
    }

    @Override
    public Date getLastModified() {
        return getLastUpdated() == null ? getCreated():getLastUpdated();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Attachment other = (Attachment) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
