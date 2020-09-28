package com.trl.springbootspringsecurityangularjwt.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Objects;

public class ExceptionDetails {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss", timezone = "Europe/Madrid")
    private Date timestamp;
    private int httpStatusCode;
    private HttpStatus httpStatus;
    private String reason;
    private String message;

    public ExceptionDetails(Builder builder) {
        this.timestamp = builder.timestamp;
        this.httpStatusCode = builder.httpStatusCode;
        this.httpStatus = builder.httpStatus;
        this.reason = builder.reason;
        this.message = builder.message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionDetails that = (ExceptionDetails) o;
        return httpStatusCode == that.httpStatusCode &&
                Objects.equals(timestamp, that.timestamp) &&
                httpStatus == that.httpStatus &&
                Objects.equals(reason, that.reason) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, httpStatusCode, httpStatus, reason, message);
    }

    @Override
    public String toString() {
        return "ExceptionDetails{" +
                "timestamp=" + timestamp +
                ", httpStatusCode=" + httpStatusCode +
                ", httpStatus=" + httpStatus +
                ", reason='" + reason + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public static class Builder {

        private final Date timestamp;
        private int httpStatusCode;
        private HttpStatus httpStatus;
        private String reason;
        private String message;

        public Builder() {
            this.timestamp = new Date();
            this.httpStatusCode = 0;
            this.httpStatus = HttpStatus.BAD_REQUEST;
            this.reason = "Default Reason.";
            this.message = "Default Message.";
        }

        public Builder withHttpStatusCode(int httpStatusCode) {
            if (httpStatusCode == 0) {
                throw new IllegalArgumentException("Parameter 'httpStatusCode' must be not equals to zero.");
            }
            this.httpStatusCode = httpStatusCode;
            return this;
        }

        public Builder withHttpStatus(HttpStatus httpStatus) {
            if (httpStatus == null) {
                throw new IllegalArgumentException("Parameter 'httpStatus' must be not equals to null.");
            }
            this.httpStatus = HttpStatus.valueOf(httpStatus.value());
            return this;
        }

        public Builder withReason(String reason) {
            if (reason == null) {
                throw new IllegalArgumentException("Parameter 'reason' must be not equals to null.");
            }
            this.reason = reason;
            return this;
        }

        public Builder withMessage(String message) {
            if (message == null) {
                throw new IllegalArgumentException("Parameter 'message' must be not equals to null.");
            }
            this.message = message;
            return this;
        }

        public ExceptionDetails build() {
            return new ExceptionDetails(this);
        }
    }
}
