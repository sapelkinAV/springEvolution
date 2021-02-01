
namespace java ru.alfabank.model

struct BookThrift {

       1: string title;
       2: list<string> authors;
       3: i32 firstPublishYear;
}