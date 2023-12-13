package exception

class CustomException(val statusCode: Int, errorMessage: String) : RuntimeException(errorMessage)
