package com.example.medcentral.model.response;

public class BaseResponse<T> {
    private int responseCode;
    private String responseCodeDescription;
    private String responseMessage;
    private T data;              // for success
    private Object error;        // for errors (can be Map<String, String> or any error object)

    public BaseResponse() {}

    private BaseResponse(Builder<T> builder) {
        this.responseCode = builder.responseCode;
        this.responseCodeDescription = builder.responseCodeDescription;
        this.responseMessage = builder.responseMessage;
        this.data = builder.data;
        this.error = builder.error;
    }

    // Getters and setters omitted for brevity

    public static class Builder<T> {
        private int responseCode;
        private String responseCodeDescription;
        private String responseMessage;
        private T data;
        private Object error;

        public Builder() {}

        public Builder<T> responseCode(int responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        public Builder<T> responseCodeDescription(String responseCodeDescription) {
            this.responseCodeDescription = responseCodeDescription;
            return this;
        }

        public Builder<T> responseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            this.error = null; // clear error if data is set
            return this;
        }

        public Builder<T> error(Object error) {
            this.error = error;
            this.data = null; // clear data if error is set
            return this;
        }

        public BaseResponse<T> build() {
            return new BaseResponse<>(this);
        }
    }

    // Getters and setters here
    public int getResponseCode() { return responseCode; }
    public void setResponseCode(int responseCode) { this.responseCode = responseCode; }

    public String getResponseCodeDescription() { return responseCodeDescription; }
    public void setResponseCodeDescription(String responseCodeDescription) { this.responseCodeDescription = responseCodeDescription; }

    public String getResponseMessage() { return responseMessage; }
    public void setResponseMessage(String responseMessage) { this.responseMessage = responseMessage; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public Object getError() { return error; }
    public void setError(Object error) { this.error = error; }
}
