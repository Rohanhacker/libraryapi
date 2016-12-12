package library


import grails.rest.*

@Resource(readOnly = false, formats = ['json', 'xml'])
class Author {
    String name
    static constraints = {
        name blank: false
    }
}