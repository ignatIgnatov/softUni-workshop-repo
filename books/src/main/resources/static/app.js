let title = document.getElementById("title");
let authorName = document.getElementById("author");
let isbn = document.getElementById("isbn");

let submitBtn = document.getElementById("add-btn");
let btnEdit = document.getElementById("btn-edit");

let editId = "";

btnEdit.style.display = "none";

$("#loadBooks").click(() => {
  reloadBooks();
});

$("#add-btn").click(() => {
  addBook();
});

$("#btn-edit").click(() => {
  editBook();
});

function addBook() {
  let temp = {
    title: title.value,
    isbn: isbn.value,
    author: {
      name: authorName.value,
    },
  };

  const httpHandlers = {
    method: "POST",
    body: JSON.stringify(temp),
    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  };

  fetch("http://localhost:8080/books", httpHandlers)
    .then(() => reloadBooks())
    .catch((err) => console.error(err));

  title.value = "";
  authorName.value = "";
  isbn.value = "";
}

function reloadBooks() {
  $("#authors-container").empty();

  fetch("http://localhost:8080/books")
    .then((response) => response.json())
    .then((json) =>
      json.forEach((book) => {
        let tableRow =
          "<tr>" +
          "<td>" +
          book.title +
          "</td>" +
          "<td>" +
          book.author.name +
          "</td>" +
          "<td>" +
          book.isbn +
          "</td>" +
          "<td>" +
          '<button class="edit-btn" data-book-id="' +
          book.id +
          '">Edit</button>' +
          '<button class="delete-btn" data-book-id="' +
          book.id +
          '">Delete</button>' +
          "</td>";

        ("</tr>");

        $("#authors-container").append(tableRow);
      })
    );
}

$("body").on("click", "button.delete-btn", function () {
  let bookId = $(this).data("book-id");

  fetch("http://localhost:8080/books/" + bookId, {
    method: "DELETE",
  }).then(() => reloadBooks());
});

$("body").on("click", "button.edit-btn", function () {
  let bookId = $(this).data("book-id");
  editId = bookId;

  fetch("http://localhost:8080/books/" + bookId)
    .then((response) => response.json())
    .then((data) => {
      title.value = data.title;
      isbn.value = data.isbn;
      author.value = data.author.name;
    });

  submitBtn.style.display = "none";
  btnEdit.style.display = "block";

});

function editBook() {
  let bookId = editId;

  let temp = {
    title: title.value,
    isbn: isbn.value,
    author: {
      name: authorName.value,
    },
  };

  const httpHandlers = {
    method: "PUT",
    body: JSON.stringify(temp),
    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  };

  fetch("http://localhost:8080/books/" + bookId, httpHandlers)
    .then(() => reloadBooks())
    .catch((err) => console.error(err));

  title.value = "";
  authorName.value = "";
  isbn.value = "";

  submitBtn.style.display = "block";
  btnEdit.style.display = "none";
}
