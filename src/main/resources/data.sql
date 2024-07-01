USE website;

INSERT INTO users (age, email, first_name, last_name, password, phone_number)
VALUES (18,'sandy@petrov.com', 'Sandy', 'Petrov', '1212', 0885);
INSERT INTO users (age, email, first_name, last_name, password, phone_number)
VALUES (18,'lily@petrova.com', 'Lily', 'Petrova', '1212', 0883);

INSERT INTO user_roles(user, roles) 
VALUES ('1', '1');
INSERT INTO user_roles(user, roles)
VALUES ('2', '1');
INSERT INTO user_roles(user, roles)
VALUES ('2', '4');

INSERT INTO health_conditions (danger_level, name, description, created_by_user_id, creation_date)
VALUES ('LOW', 'Mild Allergies', 'The patient has mild seasonal allergies. The dental treatment should ensure that any allergens potentially present in the dental office, like latex or certain cleaning products, are avoided to prevent triggering the patient''s symptoms.', 1 , TIMESTAMP(NOW()));
INSERT INTO health_conditions (danger_level, name, description, created_by_user_id, creation_date)
VALUES ('MEDIUM', 'Diabetes', 'The patient has Type 2 diabetes. The dental treatment should ensure that the patient''s blood sugar levels are monitored, and strict oral hygiene practices are maintained.', 1 , TIMESTAMP(NOW()));
INSERT INTO health_conditions (danger_level, name, description, created_by_user_id, creation_date)
VALUES ('HIGH', 'Heart Disease', 'The patient has a history of significant heart disease. The dental treatment should ensure that procedures are coordinated with the patient''s cardiologist, and special precautions, such as pre-medication with antibiotics.', 1, TIMESTAMP(NOW()));

INSERT INTO newsletter (email, creation_date)
VALUES ('mihailbozov001@gmail.com', TIMESTAMP(NOW()));
INSERT INTO newsletter (email, creation_date)
VALUES ('mihailbozov@hotmail.com', TIMESTAMP(NOW()));

INSERT INTO treatments(description, name, price, created_by_user_id, creation_date)
VALUES ('A routine procedure involving the removal of plaque and tartar from teeth to maintain oral health and prevent gum disease.', 'Dental Cleaning', '100', 1,  TIMESTAMP(NOW()));
INSERT INTO treatments(description, name, price, created_by_user_id, creation_date)
VALUES ('A procedure to remove infected or damaged tissue from inside a tooth, clean and disinfect the area, and fill it to prevent further infection.', 'Root Canal Treatment', '500', 2,  TIMESTAMP(NOW()));
INSERT INTO treatments(description, name, price, created_by_user_id, creation_date)
VALUES ('A surgical procedure where a titanium post is inserted into the jawbone to serve as a stable foundation for a replacement tooth or bridge.', 'Dental Implant Placement', '1000', 1,  TIMESTAMP(NOW()));
INSERT INTO treatments(description, name, price, created_by_user_id, creation_date)
VALUES ('A cosmetic procedure to lighten the color of your teeth and remove stains, resulting in a brighter smile.', 'Teeth Whitening', '200', 1, TIMESTAMP(NOW()));
INSERT INTO treatments(description, name, price, created_by_user_id, creation_date)
VALUES ('A cap placed over a damaged tooth to restore its shape, size, strength, and appearance.', 'Dental Crowns', '150', 2,  TIMESTAMP(NOW()));
INSERT INTO treatments(description, name, price, created_by_user_id, creation_date)
VALUES ('Devices used to correct misaligned teeth and jaws, gradually straightening teeth and improving bite over time.', 'Orthodontic Braces', '2000', 1,  TIMESTAMP(NOW()));

INSERT INTO messages (date_time, text, author_id, recipient_id)
VALUES (TIMESTAMP(NOW()), 'What is up', 1, 2);
INSERT INTO messages (date_time, text, author_id, recipient_id)
VALUES (TIMESTAMP(NOW()), 'Im fine!', 2, 1);

INSERT INTO images(name, url)
VALUES ('brackets.svg', '/images/icons/brackets.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 1);
INSERT INTO images(name, url)
VALUES ('clean.svg', '/images/icons/clean.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 2);
INSERT INTO images(name, url)
VALUES ('cleansing.svg', '/images/icons/cleansing.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 3);
INSERT INTO images(name, url)
VALUES ('crown.svg', '/images/icons/crown.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 4);
INSERT INTO images(name, url)
VALUES ('dentMachine.svg', '/images/icons/dentMachine.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 5);
INSERT INTO images(name, url)
VALUES ('diagnostic.svg', '/images/icons/diagnostic.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 6);
INSERT INTO images(name, url)
VALUES ('implant.svg', '/images/icons/implant.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 7);
INSERT INTO images(name, url)
VALUES ('quotes_white_end.svg', '/images/icons/quotes_white_end.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 8);
INSERT INTO images(name, url)
VALUES ('quotes_white_start.svg', '/images/icons/quotes_white_start.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 9);
INSERT INTO images(name, url)
VALUES ('rootCanal.svg', '/images/icons/rootCanal.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 10);
INSERT INTO images(name, url)
VALUES ('sawing.svg', '/images/icons/sawing.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 11);

INSERT INTO images(name, url)
VALUES ('hero_background.svg', '/images/main/hero_background.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (5, 12);
INSERT INTO images(name, url)
VALUES ('hero_background_green.svg', '/images/main/hero_background_green.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (5, 12);
INSERT INTO images(name, url)
VALUES ('heroImg.svg', '/images/main/heroImg.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (5, 12);
INSERT INTO images(name, url)
VALUES ('logo.svg', '/images/main/logo.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (5, 13);
INSERT INTO images(name, url)
VALUES ('pattern.svg', '/images/main/pattern.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (5, 14);
INSERT INTO images(name, url)
VALUES ('dr_Cassandra.png', '/images/profile/dr_Cassandra.png');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (1, 15);
INSERT INTO images(name, url)
VALUES ('dr_James.png', '/images/profile/dr_James.png');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (1, 16);
INSERT INTO images(name, url)
VALUES ('da_Evelyn.png', '/images/profile/da_Evelyn.png');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (1, 17);
INSERT INTO images(name, url)
VALUES ('dr_Sophia.png', '/images/profile/dr_Sophia.png');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (1, 18);
INSERT INTO images(name, url)
VALUES ('dr_Isabella.png', '/images/profile/dr_Isabella.png');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (1, 19);
INSERT INTO images(name, url)
VALUES ('da_Daniel.png', '/images/profile/da_Daniel.png');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (1, 20);
INSERT INTO images(name, url)
VALUES ('Yanica_img.png', '/images/profile/Yanica_img.png');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (1, 21);
INSERT INTO images(name, url)
VALUES ('Emma_img.png', '/images/profile/Emma_img.png');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (1, 22);
INSERT INTO images(name, url)
VALUES ('Kale_img.png', '/images/profile/Kale_img.png');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (1, 23);
INSERT INTO images(name, url)
VALUES ('doctor.jpg', '/images/pictures/doctor.jpg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (6, 24);



