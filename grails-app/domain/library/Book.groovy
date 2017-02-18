package library

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.*

@Resource(readOnly = false, formats = ['json', 'xml'])
class Book {
    String title
    int edition
    String isbn
    Publisher publisher
    String userId
    static hasMany = [author: Author]
    static constraints = {
        title blank: false, unique: true
        isbn blank: false, unique: true
    }
}