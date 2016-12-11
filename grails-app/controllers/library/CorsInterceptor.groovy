package library


class CorsInterceptor {

    CorsInterceptor() {
        matchAll()
    }

    boolean before() {
        header( "Access-Control-Allow-Origin", "http://localhost:8080" )
        boolean options = ("OPTIONS" == request.method)
        if (options) {

            header( "Access-Control-Allow-Origin", "http://localhost:4200" )
            header( "Access-Control-Allow-Credentials", "true" )
            header( "Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
            header( "Access-Control-Max-Age", "3600" )

            response.status = 200
        }

        true
    }

    boolean after() { true }

}