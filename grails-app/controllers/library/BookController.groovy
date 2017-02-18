package library

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import grails.transaction.Transactional
import org.hibernate.annotations.FetchMode

class BookController extends RestfulController {
	static responseFormats = ['json', 'xml']
	BookController() {
        super(Book)
    }
    def springSecurityService

    @Transactional(readOnly = true)
    def index() {
        respond Book.list(max: params.max, offset: params.offset)
    }
    @Secured(value=["hasRole('ROLE_USER')"], httpMethod='POST')
    @Transactional
    def save(Book book) {
        def authorList = []
        book.author.each {
            def tempAuthor = Author.findByName(it.name)
            if (tempAuthor == null) {
                tempAuthor = new Author(name: it.name)
            }
            authorList.push(tempAuthor)
        }
        def tempPublisher = Publisher.findByName(book.publisher.name)
        if(tempPublisher==null) {
            tempPublisher = new Publisher(name: book.publisher.name)
        }

        def newBook = new Book(title: book.title, edition: book.edition, isbn: book.isbn, author: authorList, publisher: tempPublisher, userId: (String)springSecurityService.principal.username)
        newBook.save()
        respond newBook
    }

    def searchByName() {
        def results = Book.findAllByTitleLike("%"+params.key+"%", [max: 10, sort: "title"])
        respond results
    }

    def pages() {
        int results = Book.count();
        respond([results])
    }

    @Secured(value=["hasRole('ROLE_USER')"])
    def delete(Book book) {
        println(book)
        def b = Book.findByIsbn(book.isbn);
        if(b.userId == (String)springSecurityService.principal.username) {
            b.delete();
        }
        respond([1]);
    }

//    @Secured(value=["hasRole('ROLE_USER')"], httpMethod='POST')
//    @Transactional
//    def update(Book book) {
//        def b = Book.findByIsbn(book.isbn);
//        if(b.userId == (String)springSecurityService.principal.username) {
//            b.delete();
//        }
//        def authorList = []
//        book.author.each {
//            def tempAuthor = Author.findByName(it.name)
//            if (tempAuthor == null) {
//                tempAuthor = new Author(name: it.name)
//            }
//            authorList.push(tempAuthor)
//        }
//        def tempPublisher = Publisher.findByName(book.publisher.name)
//        if(tempPublisher==null) {
//            tempPublisher = new Publisher(name: book.publisher.name)
//        }
//        def newBook = new Book(id: book.id ,title: book.title, edition: book.edition, isbn: book.isbn, author: authorList, publisher: tempPublisher, userId: (String)springSecurityService.principal.username)
//        newBook.save()
//        respond newBook
//    }
}
