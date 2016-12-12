package library

import grails.rest.RestfulController
import grails.transaction.Transactional

class BookController extends RestfulController {
	static responseFormats = ['json', 'xml']
	BookController() {
        super(Book)
    }
    @Transactional(readOnly = true)
    def index() {
        respond Book.list()
    }
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

        def newBook = new Book(title: book.title, edition: book.edition, isbn: book.isbn, author: authorList, publisher: tempPublisher)
        newBook.save()
        respond newBook
    }

    def searchByName() {
        def results = Book.findAllByTitleLike("%"+params.key+"%")
        respond results
    }
}
