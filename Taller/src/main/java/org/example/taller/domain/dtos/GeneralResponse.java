package org.example.taller.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor

public class GeneralResponse {
    private String message;
    private Object data;

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private String message;
        private Object data;
        private HttpStatus status = HttpStatus.OK;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public Builder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ResponseEntity<GeneralResponse> build() {
            if (this.message == null) {
                this.message = this.status.getReasonPhrase();
            }

            return new ResponseEntity<>(new GeneralResponse(this.message, this.data), this.status);
        }
    }
}
