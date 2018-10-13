include "BookThrift.thrift"

namespace java ru.alfabank.thrift

service OpenLibraryService {
    list<BookThrift.BookThrift> getBooks(1:string query);
    list<BookThrift.BookThrift> getBooksByAuthor(1:string author);
}