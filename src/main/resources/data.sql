USE website;

INSERT INTO health_conditions (danger_level, name, description)
VALUES ('LOW', 'Mild Allergies', 'The patient has mild seasonal allergies. The dental treatment should ensure that any allergens potentially present in the dental office, like latex or certain cleaning products, are avoided to prevent triggering the patient''s symptoms.');
INSERT INTO health_conditions (danger_level, name, description)
VALUES ('MEDIUM', 'Diabetes', 'The patient has Type 2 diabetes. The dental treatment should ensure that the patient''s blood sugar levels are monitored, and strict oral hygiene practices are maintained.');
INSERT INTO health_conditions (danger_level, name, description)
VALUES ('HIGH', 'Heart Disease', 'The patient has a history of significant heart disease. The dental treatment should ensure that procedures are coordinated with the patient''s cardiologist, and special precautions, such as pre-medication with antibiotics.');

INSERT INTO newsletter (email)
VALUES ('mihailbozov001@gmail.com');
INSERT INTO newsletter (email)
VALUES ('mihailbozov@hotmail.com');

INSERT INTO roles (name)
VALUES ('Admin');
INSERT INTO roles (name)
VALUES ('Manager');
INSERT INTO roles (name)
VALUES ('Doctor');
INSERT INTO roles (name)
VALUES ('User');

INSERT INTO treatments(description, name, price)
VALUES ('A routine procedure involving the removal of plaque and tartar from teeth to maintain oral health and prevent gum disease.', 'Dental Cleaning', '100');
INSERT INTO treatments(description, name, price)
VALUES ('A procedure to remove infected or damaged tissue from inside a tooth, clean and disinfect the area, and fill it to prevent further infection.', 'Root Canal Treatment', '500');
INSERT INTO treatments(description, name, price)
VALUES ('A surgical procedure where a titanium post is inserted into the jawbone to serve as a stable foundation for a replacement tooth or bridge.', 'Dental Implant Placement', '1000');
    
INSERT INTO treatments(description, name, price)
VALUES ('A cosmetic procedure to lighten the color of your teeth and remove stains, resulting in a brighter smile.', 'Teeth Whitening', '200');
INSERT INTO treatments(description, name, price)
VALUES ('A cap placed over a damaged tooth to restore its shape, size, strength, and appearance.', 'Dental Crowns', '150');
INSERT INTO treatments(description, name, price)
VALUES ('Devices used to correct misaligned teeth and jaws, gradually straightening teeth and improving bite over time.', 'Orthodontic Braces', '2000');


INSERT INTO users (age, email, first_name, last_name, password, picture_url) 
VALUES (18,'ggg@gmail.com', 'Mony', 'Petrov', 'a12345', 'urlP');
INSERT INTO users (age, email, first_name, last_name, password, picture_url)
VALUES (18,'bbb@gmail.com', 'Alex', 'Ivanov', 'a12345', 'urlP');

INSERT INTO messages (date_time, text, author_id, recipient_id)
VALUES (NOW(), 'What is up', 1, 2);
INSERT INTO messages (date_time, text, author_id, recipient_id)
VALUES (NOW(), 'Im fine!', 2, 1);
