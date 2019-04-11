package com.knight.taiepizoo.model;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.knight.taiepizoo.model.Status.ERROR;
import static com.knight.taiepizoo.model.Status.LOADING;
import static com.knight.taiepizoo.model.Status.SUCCESS;


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
public class  Resource<T> {
    @NonNull ResourceType type;

    @NonNull
    public final Status status;

    @Nullable
    public final String message;

    @Nullable
    public final T data;

    public Resource(@NonNull ResourceType type, @NonNull Status status, @Nullable T data, @Nullable String message) {
        this.type = type;
        this.status = status;
        this.data = data;
        this.message = message;
    }




    @NonNull
    public ResourceType getType() {
        return type;
    }

    @NonNull
    public Status getStatus() {
        return status;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    @Nullable
    public T getData() {
        return data;
    }

    public static <T> Resource<T> success(@NonNull ResourceType type, @Nullable T data) {
        return new Resource<>(type, SUCCESS, data, null);
    }

    public static <T> Resource<T> error(@NonNull ResourceType type, String msg, @Nullable T data) {
        return new Resource<>(type, ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@NonNull ResourceType type, @Nullable T data) {
        return new Resource<>(type, LOADING, data, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Resource<?> resource = (Resource<?>) o;

        if (status != resource.status) {
            return false;
        }
        if (message != null ? !message.equals(resource.message) : resource.message != null) {
            return false;
        }
        return data != null ? data.equals(resource.data) : resource.data == null;
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public boolean isSuccess() {
        return status == SUCCESS;
    }
}