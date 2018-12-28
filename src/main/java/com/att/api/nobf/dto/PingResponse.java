package com.att.api.nobf.dto;

public class PingResponse extends BaseResponse {
    private String message;

    private PingResponse() {}

    public PingResponse(final ResponseHeader header, final String message) {
        super(header);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PingResponse)) return false;
        if (!super.equals(o)) return false;

        PingResponse that = (PingResponse) o;

        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PingResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
