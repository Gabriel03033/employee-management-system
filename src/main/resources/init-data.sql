INSERT INTO mentors (name, email, password, mobile, address, birthday, available, number_of_employees) VALUES
('Stefan Petrescu', 'stefan@gmail.com', 'stefan1234', '0145236547', 'B-dul. Învățătorului', '1990-01-10', true, 6),
('Radu Buzoianu', 'b.radu@gmail.com', 'radu1973', '0754351587', 'Strada Grivitei', '2000-11-15', true, 6),
('Maria Dobrica', 'MariaDobre@gmail.com', 'stefan1234', '0766479221', 'B-dul. Învățătorului', '1991-02-4', true, 6),
('Marius Pușcașu', 'mariusP@gmail.com', 'stefan1234', '0363946966', 'Splaiul Decebal', '1990-01-01', true, 6),
('Victor Florea', 'cristobal.rosu@yahoo.com', 'stefan1234', '0243408602 ', 'B-dul. Independenței', '1995-5-20', true, 6);

INSERT INTO studies (university, faculty, major) VALUES
('University of Transylvania', 'Faculty of Electrical Engineering and Computer Science', 'Automatic and Applied Computer Science'),
('University of Spiru Haret', 'Faculty of Legal and Administrative Sciences', 'Automatic and Applied Computer Science'),
('Christian university Dimitrie Cantemir', 'Faculty of International Economic Relations', 'Automatic and Applied Computer Science'),
('University of Transylvania', 'Faculty of Literature and Cultural Studies', 'Automatic and Applied Computer Science'),
('University of Transylvania', 'Faculty of Technological Engineering and Industrial Management', 'Automatic and Applied Computer Science');

INSERT INTO employees (name, email, password, mobile, address, birthday, job_type, position, grade, mentor_id, studies_id) VALUES
('Bacalu George', 'georgebacalu@gmail.com', 'george', '0147354687', 'Prunului', '2001-01-01', 'FULL_TIME', 'DEVOPS', 'JUNIOR', 2, 5 ),
('Firea Gabriel', 'fireagabi@gmail.com', 'firea', '0745351268', 'Alunului', '2002-01-01', 'FULL_TIME', 'BACKEND', 'JUNIOR', 3, 1 ),
('Mares Andrei', 'andreiM@gmail.com', 'andrei', '0748762174', 'Brazilor', '2003-01-01', 'FULL_TIME', 'DEVOPS', 'JUNIOR', 4, 4 ),
('Corbea Dobre', 'corbeadobre@gmail.com', 'corbea', '0741324978', 'Neptun', '2004-01-01', 'FULL_TIME', 'BACKEND', 'JUNIOR', 3, 5 ),
('Piciu Manea', 'piciumanea@gmail.com', 'piciu', '0743189523', 'Saturn', '1991-01-01', 'FULL_TIME', 'DEVOPS', 'JUNIOR', 2, 3 ),
('Vancica Adrian', 'vancicaadri@gmail.com', 'vancica', '0743976423', 'Florilor', '1992-01-01', 'PART_TIME', 'DEVOPS', 'JUNIOR', 1, 1 ),
('Andi Vasluianu', 'andiandi@gmail.com', 'andi01', '0745735123', 'Levanticai', '1930-01-01', 'PART_TIME', 'BACKEND', 'JUNIOR', 2, 4 ),
('Dragoș Bucur', 'bucurDragos@gmail.com', 'bucur', '0746314578', 'Craiova', '1994-01-01', 'PART_TIME', 'DEVOPS', 'JUNIOR', 3, 1 ),
('Florin Piersic', 'florinpiersic@gmail.com', 'florin', '0746632145', 'Nucului', '1995-01-01', 'PART_TIME', 'BACKEND', 'JUNIOR', 4, 1 ),
('Diana Dumitrescu', 'dianaDumitru@gmail.com', 'diana', '0789456113', 'Avram', '1996-01-01', 'PART_TIME', 'DEVOPS', 'MID', 5, 1 ),
('Alin Panc', 'alinPanc@gmail.com', 'alin', '0745125478', 'Craiului', '1997-01-01', 'PART_TIME', 'DEVOPS', 'MID', 2, 1 ),
('Gheorghe Dinică', 'gheorghe@gmail.com', 'gheorghe', '0741212316', 'Branduselor', '1998-01-01', 'PART_TIME', 'BACKEND', 'MID', 1, 3 ),
('Mircea Albulescu', 'albulescuMircea@gmail.com', 'albulescu', '0741715487', 'Lamaitei', '1998-01-01', 'PART_TIME', 'DEVOPS', 'MID', 1, 5 ),
('Cristina Ciobănașu', 'cristinaCris@gmail.com', 'cristina', '0745328945', 'Hunedoara', '1980-01-01', 'CONTRACT', 'BACKEND', 'MID', 2, 4 ),
('Toma Caragiu', 'tomaCaragiu@gmail.com', 'toma', '0746135469', 'Oradea', '1982-01-01', 'CONTRACT', 'DESIGN', 'MID', 2, 3 ),
('Iurie Darie', 'iurieDarie@gmail.com', 'iurie', '0754798653', 'Ploiesti', '1983-01-01', 'CONTRACT', 'DESIGN', 'MID', 5, 2 ),
('Stela Popescu', 'stelaPopescu@gmail.com', 'stela', '0742456879', 'Suceava', '1984-01-01', 'CONTRACT', 'DESIGN', 'MID', 3, 4 ),
('Mihai Călin', 'mihaiCalin@gmail.com', 'mihai', '07423515235', 'Focsani', '1985-01-01', 'CONTRACT', 'DESIGN', 'MID', 5, 4 ),
('Dan Bordeianu', 'danbordeianu@gmail.com', 'bordeianu', '0745215765', 'Arad', '1986-01-01', 'CONTRACT', 'DESIGN', 'SENIOR', 5, 5 ),
('Jean Constantin', 'constantinJean@gmail.com', 'constantin', '0745315865', 'Campulung', '1987-01-01', 'INTERN', 'DESIGN', 'SENIOR', 4, 3 ),
('Radu Beligan', 'Beligan@gmail.com', 'beligan', '0748651214', 'Bacau', '1988-01-01', 'INTERN', 'DESIGN', 'JUNIOR', 2, 1 ),
('Cătălina Mustață', 'catalina@gmail.com', 'catalina', '0748635115', 'Ilfov', '1989-01-01', 'INTERN', 'DESIGN', 'SENIOR', 3, 1 ),
('Bogdan Albulescu', 'bogdanAlbu@gmail.com', 'bogdan', '0745356847', 'Snagov', '1970-01-01', 'INTERN', 'DESIGN', 'SENIOR', 1, 1 ),
('Sergiu Nicolaescu', 'sergiuNicolae@gmail.com', 'sergiu', '0741321548', 'Dej', '1972-01-01', 'INTERN', 'FRONTEND', 'SENIOR', 5, 2 ),
('Victor Rebengiuc', 'victor@gmail.com', 'victor', '0741254451', ' Craiova', '1973-01-01', 'INTERN', 'FRONTEND', 'SENIOR', 2, 1 ),
('Elvira Deatcu', 'Elvira@gmail.com', 'elvira', '0741256347', 'MEHEDINŢI', '1974-01-01', 'INTERN', 'FRONTEND', 'SENIOR',3, 1 ),
('Carmen Tănase', 'tanaseCarmen@gmail.com', 'tanase', '0748965214', 'Suceava', '1975-01-01', 'INTERN', 'FRONTEND', 'SENIOR', 2, 4 ),
('Marin Moraru', 'MoraruMarin@gmail.com', 'morarru', '0854235147', 'Bucuresti', '1976-01-01', 'FULL_TIME', 'FRONTEND', 'SENIOR', 5, 2 ),
('Ștefan Iordache', 'iordacheStefan@gmail.com', 'iordache', '0548723154', 'Urziceni', '1977-01-01', 'FULL_TIME', 'FRONTEND', 'SENIOR', 4, 2 ),
('Gheorghe Visu', 'ghergheVisu@gmail.com', 'gheorghe', '0874235145', 'Vaslui', '1978-01-01', 'FULL_TIME', 'FRONTEND', 'SENIOR', 1, 1 ),
('Claudiu Bleonţ', 'cladiuBleont@gmail.com', 'claudiu', '0745318654', 'Bistrita', '1979-01-01', 'FULL_TIME', 'FRONTEND', 'SENIOR', 3, 2 ),
('Marcel Iureș', 'iuresMarcel@gmail.com', 'iures', '0840215478', 'Pitesti', '1972-01-01', 'FULL_TIME', 'FRONTEND', 'SENIOR', 5, 4 );



