package library


import grails.rest.*

@Resource(readOnly = false, formats = ['json', 'xml'])
class Book {
    String title
    int edition
    Publisher publisher
    Author author
}