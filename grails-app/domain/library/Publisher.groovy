package library


import grails.rest.*

@Resource(readOnly = false, formats = ['json', 'xml'])
class Publisher {
    String name
    static constraints = {
        name blank: false
    }
}