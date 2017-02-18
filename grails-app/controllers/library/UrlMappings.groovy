package library

class UrlMappings {

    static mappings = {
        put "/api/$controller/$id(.$format)?"(action:"update")
        get "/api/book"(controller: 'book', action:'index')
        post "/api/create"(controller: 'book', action:'save')
        post "/api/delete"(controller: 'book', action:'delete') // todo try to make delete work
        get "/book/search"(controller: 'book', action: 'searchByName')
        get "/api/pages"(controller: 'book', action:'pages')
        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
