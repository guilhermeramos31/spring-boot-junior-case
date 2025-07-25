INSERT INTO AUTHOR (created_at, updated_at, name, birth_date, email) VALUES
                                                                         (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'J.K. Rowling', '1965-07-31', 'jk.rowling@example.com'),
                                                                         (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'George R.R. Martin', '1948-09-20', 'grrm@example.com'),
                                                                         (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Stephen King', '1947-09-21', 'stephen.king@example.com'),
                                                                         (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Agatha Christie', '1890-09-15', 'agatha.christie@example.com'),
                                                                         (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Yuval Noah Harari', '1976-02-24', 'yuval.harari@example.com'),
                                                                         (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Gabriel Garcia Marquez', '1927-03-06', 'gabriel.marquez@example.com'),
                                                                         (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Machado de Assis', '1839-06-21', 'machado.assis@example.com');

INSERT INTO CATEGORY (created_at, updated_at, name, description) VALUES
                                                                     (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Fantasia', 'Gênero literário que apresenta elementos mágicos, mitológicos e sobrenaturais em um mundo imaginário.'),
                                                                     (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Ficção Científica', 'Gênero que explora conceitos imaginativos e futurísticos, como avanços científicos e tecnológicos.'),
                                                                     (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Terror', 'Gênero focado em provocar medo, suspense e repulsa no leitor.'),
                                                                     (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Mistério', 'Gênero onde o enredo gira em torno de um enigma ou crime a ser desvendado.'),
                                                                     (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'História', 'Livros que narram eventos passados, reais ou fictícios, com foco em fatos e períodos históricos.'),
                                                                     (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Literatura Clássica', 'Obras literárias de grande importância cultural e artística, que transcendem o tempo.'),
                                                                     (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Romance', 'Gênero focado em histórias de amor, aventura e desenvolvimento de personagens e seus relacionamentos.');

INSERT INTO BOOK (created_at, updated_at, title, isbn, year_published, price, author_id, category_id) VALUES
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Harry Potter e a Pedra Filosofal', '9788532511010', 1997, 35.90, 1, 1),
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Harry Potter e a Câmara Secreta', '9788532511027', 1998, 38.50, 1, 1),
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Harry Potter e o Prisioneiro de Azkaban', '9788532511034', 1999, 42.00, 1, 1);

INSERT INTO BOOK (created_at, updated_at, title, isbn, year_published, price, author_id, category_id) VALUES
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A Guerra dos Tronos', '9788579800688', 1996, 65.99, 2, 1),
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A Fúria dos Reis', '9788579800886', 1998, 68.00, 2, 1);

INSERT INTO BOOK (created_at, updated_at, title, isbn, year_published, price, author_id, category_id) VALUES
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'It: A Coisa', '9788581050529', 1986, 75.00, 3, 3),
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'O Iluminado', '9788581050413', 1977, 60.00, 3, 3),
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Cemitério Maldito', '9788581050451', 1983, 55.00, 3, 3);

INSERT INTO BOOK (created_at, updated_at, title, isbn, year_published, price, author_id, category_id) VALUES
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'E Não Sobrou Nenhum', '9788521900138', 1939, 49.90, 4, 4),
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'O Assassinato de Roger Ackroyd', '9788521900084', 1926, 45.00, 4, 4);

INSERT INTO BOOK (created_at, updated_at, title, isbn, year_published, price, author_id, category_id) VALUES
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Sapiens: Uma Breve História da Humanidade', '9788532525048', 2011, 89.90, 5, 5),
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Homo Deus: Uma Breve História do Amanhã', '9788532530752', 2015, 95.00, 5, 5);

INSERT INTO BOOK (created_at, updated_at, title, isbn, year_published, price, author_id, category_id) VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A Tormenta de Espadas', '9788579800930', 2000, 72.50, 2, 1);

INSERT INTO BOOK (created_at, updated_at, title, isbn, year_published, price, author_id, category_id) VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'O Cemitério', '9788581050451', 1983, 58.00, 3, 3);

INSERT INTO BOOK (created_at, updated_at, title, isbn, year_published, price, author_id, category_id) VALUES
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Harry Potter e o Cálice de Fogo', '9788532511041', 2000, 48.00, 1, 1),
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Harry Potter e a Ordem da Fênix', '9788532511058', 2003, 55.00, 1, 1);

INSERT INTO BOOK (created_at, updated_at, title, isbn, year_published, price, author_id, category_id) VALUES
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Cem Anos de Solidão', '9788501016843', 1967, 59.90, 6, 7),
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'O Amor nos Tempos do Cólera', '9788501025531', 1985, 54.90, 6, 7);

INSERT INTO BOOK (created_at, updated_at, title, isbn, year_published, price, author_id, category_id) VALUES
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Dom Casmurro', '9788573210996', 1899, 30.00, 7, 6),
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Memórias Póstumas de Brás Cubas', '9788573211009', 1881, 32.00, 7, 6);

INSERT INTO BOOK (created_at, updated_at, title, isbn, year_published, price, author_id, category_id) VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Assassinato no Expresso do Oriente', '9788521900145', 1934, 50.00, 4, 4);

INSERT INTO BOOK (created_at, updated_at, title, isbn, year_published, price, author_id, category_id) VALUES
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Harry Potter e o Enigma do Príncipe', '9788532511065', 2005, 58.00, 1, 1),
                                                                                                          (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Harry Potter e as Relíquias da Morte', '9788532511072', 2007, 62.00, 1, 1);

INSERT INTO BOOK (created_at, updated_at, title, isbn, year_published, price, author_id, category_id) VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '21 Lições para o Século 21', '9788532531391', 2018, 85.00, 5, 5);

INSERT INTO BOOK (created_at, updated_at, title, isbn, year_published, price, author_id, category_id) VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A Dança da Morte', '9788581050550', 1978, 68.00, 3, 3);