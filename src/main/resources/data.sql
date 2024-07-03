
USE website;


# ---------------------------     Roles     ---------------------------


INSERT INTO roles ( name, value)
VALUES ('ADMIN', 'administrator');
INSERT INTO roles ( name, value)
VALUES ('MANAGER', 'manager');
INSERT INTO roles ( name, value)
VALUES ('DENTIST', 'dentist');
INSERT INTO roles ( name, value)
VALUES ('DENTAL_ASSISTANT', 'dental assistant');
INSERT INTO roles ( name, value)
VALUES ('USER', 'userEntity');


# ---------------------------     Newsletter     ---------------------------


INSERT INTO newsletter (newsletter_email, creation_date)
VALUES ('mihailbozov001@gmail.com', TIMESTAMP(NOW()));
INSERT INTO newsletter (newsletter_email, creation_date)
VALUES ('mihailbozov@hotmail.com', TIMESTAMP(NOW()));


# ---------------------------     Images     ---------------------------


INSERT INTO images(name, url)
VALUES ('brackets.svg', '/images/treatments/brackets.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 1);
INSERT INTO images(name, url)
VALUES ('clean.svg', '/images/treatments/clean.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 2);
INSERT INTO images(name, url)
VALUES ('cleansing.svg', '/images/treatments/cleansing.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 3);
INSERT INTO images(name, url)
VALUES ('crown.svg', '/images/treatments/crown.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 4);
INSERT INTO images(name, url)
VALUES ('implant.svg', '/images/treatments/implant.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 5);
INSERT INTO images(name, url)
VALUES ('rootCanal.svg', '/images/treatments/rootCanal.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 6);
INSERT INTO images(name, url)
VALUES ('success.svg', '/images/icons/success.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 7);
INSERT INTO images(name, url)
VALUES ('success.svg', '/images/icons/success.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 8);
INSERT INTO images(name, url)
VALUES ('quotes_white_end.svg', '/images/icons/quotes_white_end.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 9);
INSERT INTO images(name, url)
VALUES ('quotes_white_start.svg', '/images/icons/quotes_white_start.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (3, 10);
INSERT INTO images(name, url)
VALUES ('heroImg.svg', '/images/main/heroImg.svg');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (5, 11);
INSERT INTO images(name, url)
VALUES ('hero_background.svg', '/images/main/hero_background.svg');
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
VALUES ('dr_Cassandra.png', '/images/profiles/dr_Cassandra.png');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (1, 15);
INSERT INTO images(name, url)
VALUES ('dr_James.png', '/images/profiles/dr_James.png');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (1, 16);
INSERT INTO images(name, url)
VALUES ('da_Evelyn.png', '/images/profiles/da_Evelyn.png');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (1, 17);
INSERT INTO images(name, url)
VALUES ('dr_Sophia.png', '/images/profiles/dr_Sophia.png');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (1, 18);
INSERT INTO images(name, url)
VALUES ('dr_Isabella.png', '/images/profiles/dr_Isabella.png');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (1, 19);
INSERT INTO images(name, url)
VALUES ('da_Daniel.png', '/images/profiles/da_Daniel.png');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (1, 20);
INSERT INTO images(name, url)
VALUES ('Yanica_img.png', '/images/profiles/Yanica_img.png');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (1, 21);
INSERT INTO images(name, url)
VALUES ('Emma_img.png', '/images/profiles/Emma_img.png');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (1, 22);
INSERT INTO images(name, url)
VALUES ('Kale_img.png', '/images/profiles/Kale_img.png');
INSERT INTO image_categories_images(image_category_id, image_id)
VALUES (1, 23);


# ---------------------------     Testimonials     ---------------------------


INSERT INTO testimonials(content, count_stars)
VALUES ('Fantastic experience! The clinic is state-of-the-art and the staff are extremely professional and caring. I highly recommend them.', 5);
INSERT INTO testimonials(content, count_stars)
VALUES ('From the moment I walked in, I felt welcomed and comfortable. The staff explained everything clearly and the service was top-notch.', 5);
INSERT INTO testimonials(content, count_stars)
VALUES ('I was nervous about my procedure, but the team was so reassuring and gentle. The results exceeded my expectations. Thank you!', 5);


# ---------------------------     Users     ---------------------------


INSERT INTO userEntities (email, first_name, last_name, password, image_id)
VALUES ('cassandra@sofia.com', 'Cassandra', 'Sofia', '1212', 15);
INSERT INTO userEntities (email, first_name, last_name, password, image_id)
VALUES ('james@sofia.com', 'James', 'Sofia', '1212', 16);
INSERT INTO userEntities (email, first_name, last_name, password, image_id)
VALUES ('evelyn@sofia.com', 'Evelyn', 'Sofia', '1212', 17);
INSERT INTO userEntities (email, first_name, last_name, password, image_id)
VALUES ('sophia@sofia.com', 'Sophia', 'Sofia', '1212', 18);
INSERT INTO userEntities (email, first_name, last_name, password, image_id)
VALUES ('isabella@sofia.com', 'Isabella', 'Sofia', '1212', 19);
INSERT INTO userEntities (email, first_name, last_name, password, image_id)
VALUES ('daniel@sofia.com', 'Daniel', 'Sofia', '1212', 20);
INSERT INTO userEntities (email, first_name, last_name, password, image_id, testimonial_id)
VALUES ('yanica@sofia.com', 'Yanica', 'Sofia', '1212', 21, 1);
INSERT INTO userEntities (email, first_name, last_name, password, image_id, testimonial_id)
VALUES ('emma@sofia.com', 'Emma', 'Sofia', '1212', 22, 2);
INSERT INTO userEntities (email, first_name, last_name, password, image_id, testimonial_id)
VALUES ('kale@sofia.com', 'Kale', 'Sofia', '1212', 23, 3);

# ---------------------------     Users Roles     ---------------------------


INSERT INTO user_roles(userEntity, roles)
VALUES ('1', '1');
INSERT INTO user_roles(userEntity, roles)
VALUES ('1', '3');
INSERT INTO user_roles(userEntity, roles)
VALUES ('2', '1');
INSERT INTO user_roles(userEntity, roles)
VALUES ('2', '3');
INSERT INTO user_roles(userEntity, roles)
VALUES ('3', '1');
INSERT INTO user_roles(userEntity, roles)
VALUES ('3', '4');
INSERT INTO user_roles(userEntity, roles)
VALUES ('4', '1');
INSERT INTO user_roles(userEntity, roles)
VALUES ('4', '3');
INSERT INTO user_roles(userEntity, roles)
VALUES ('5', '1');
INSERT INTO user_roles(userEntity, roles)
VALUES ('5', '3');
INSERT INTO user_roles(userEntity, roles)
VALUES ('6', '1');
INSERT INTO user_roles(userEntity, roles)
VALUES ('6', '4');
INSERT INTO user_roles(userEntity, roles)
VALUES ('7', '1');
INSERT INTO user_roles(userEntity, roles)
VALUES ('8', '1');
INSERT INTO user_roles(userEntity, roles)
VALUES ('9', '1');


# ---------------------------     Health Conditions     ---------------------------


INSERT INTO health_conditions (danger_level, name, description, created_by_user_id, creation_date)
VALUES ('LOW', 'Mild Allergies', 'The patient has mild seasonal allergies. The dental treatment should ensure that any allergens potentially present in the dental office, like latex or certain cleaning products, are avoided to prevent triggering the patient''s symptoms.', 1 , TIMESTAMP(NOW()));
INSERT INTO health_conditions (danger_level, name, description, created_by_user_id, creation_date)
VALUES ('MEDIUM', 'Diabetes', 'The patient has Type 2 diabetes. The dental treatment should ensure that the patient''s blood sugar levels are monitored, and strict oral hygiene practices are maintained.', 1 , TIMESTAMP(NOW()));
INSERT INTO health_conditions (danger_level, name, description, created_by_user_id, creation_date)
VALUES ('HIGH', 'Heart Disease', 'The patient has a history of significant heart disease. The dental treatment should ensure that procedures are coordinated with the patient''s cardiologist, and special precautions, such as pre-medication with antibiotics.', 1, TIMESTAMP(NOW()));


# ---------------------------     Treatments     ---------------------------


INSERT INTO treatments(description, name, price, created_by_user_id, creation_date, image_id)
VALUES ('A routine procedure involving the removal of plaque and tartar from teeth to maintain oral health and prevent gum disease.', 'Dental Cleaning', '100', 1,  TIMESTAMP(NOW()), 3);
INSERT INTO treatments(description, name, price, created_by_user_id, creation_date, image_id)
VALUES ('A procedure to remove infected or damaged tissue from inside a tooth, clean and disinfect the area, and fill it to prevent further infection.', 'Root Canal Treatment', '500', 2,  TIMESTAMP(NOW()), 6);
INSERT INTO treatments(description, name, price, created_by_user_id, creation_date, image_id)
VALUES ('A surgical procedure where a titanium post is inserted into the jawbone to serve as a stable foundation for a replacement tooth or bridge.', 'Dental Implant Placement', '1000', 1,  TIMESTAMP(NOW()), 5);
INSERT INTO treatments(description, name, price, created_by_user_id, creation_date, image_id)
VALUES ('A cosmetic procedure to lighten the color of your teeth and remove stains, resulting in a brighter smile.', 'Teeth Whitening', '200', 1, TIMESTAMP(NOW()), 2);
INSERT INTO treatments(description, name, price, created_by_user_id, creation_date, image_id)
VALUES ('A cap placed over a damaged tooth to restore its shape, size, strength, and appearance.', 'Dental Crowns', '150', 2,  TIMESTAMP(NOW()), 4);
INSERT INTO treatments(description, name, price, created_by_user_id, creation_date, image_id)
VALUES ('Devices used to correct misaligned teeth and jaws, gradually straightening teeth and improving bite over time.', 'Orthodontic Braces', '2000', 1,  TIMESTAMP(NOW()), 1);


# ---------------------------     Messages     ---------------------------


INSERT INTO messages (date_time, text, author_id, recipient_id)
VALUES (TIMESTAMP(NOW()), 'What is up', 1, 2);
INSERT INTO messages (date_time, text, author_id, recipient_id)
VALUES (TIMESTAMP(NOW()), 'Im fine!', 2, 1);
