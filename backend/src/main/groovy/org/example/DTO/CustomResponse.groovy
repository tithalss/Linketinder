package org.example.DTO

class CustomResponse {
    int status
    String message
    Object data

    CustomResponse(int status, String message, Object data = null) {
        this.status = status
        this.message = message
        this.data = data
    }
}