package library

import grails.converters.JSON

class BootStrap {


    def init = { servletContext ->
        if (!Book.count()) {
            new Book(title: "Goodnight Moon", edition: 1, isbn: "0694003611", publisher: new Publisher(name: "HarperFestival"), author: [new Author(name: "Margaret Wise Brown")], userId: "212021445860739").save(failOnError: true)
            new Book(title: "Little Blue Truck board book", edition: 1, isbn: "0544568036", publisher: new Publisher(name: "HMH Books for Young Readers"), author: [new Author(name: "Jill McElmurry"),new Author(name: "Alice Schertle")], userId: "212021445860739").save(failOnError: true)
            new Book(title: "A Wrinkle in Time (A Wrinkle in Time Quintet) ", edition: 1, isbn: "0994003611", publisher: new Publisher(name: "Square Fish"), author: [new Author(name: "Madeleine L'Engle")], userId: "212021445860739").save(failOnError: true)
            new Book(title: "Catching Fire", edition: 2, isbn: "0694003359", publisher: new Publisher(name: "Suzanne Collins"), author: [new Author(name: "Scholastic Press")], userId: "212021445860739").save(failOnError: true)
        }
    }
//            {
//                "id": 2,
//                "author": [
//                    {
//                        "id": 2,
//                        "name": "woo oww"
//                    }
//            ],
//                "edition": 1,
//                "isbn": "122blah",
//                "publisher": {
//                "id": 2,
//                "name": "nk"
//            },
//                "title": "ENGLISH",
//                "userId": "212021445860739"
//            },
    def destroy = {
    }
}
