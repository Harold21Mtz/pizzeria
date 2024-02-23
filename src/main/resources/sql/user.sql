INSERT INTO main.user (user_administrator, locked, created_at, updated_at, user_id, user_name, profile_image, user_password, login_attempts, login_attempts_mfa, code_verification, created_code_verification) VALUES(true, false, now(), now(),'2f82e69e-887d-4360-ad94-151d060d3350', 'harold21', 'Imagen', '$2y$10$SccF/uGAlvKp3i/2qB8M6u1dqMo5A/c0.avXbsNTDMGKppUmhn5Pm', 0, 0, null, now());
INSERT INTO main.user (user_administrator, locked, created_at, updated_at, user_id, user_name, profile_image, user_password,login_attempts, login_attempts_mfa, code_verification, created_code_verification) VALUES(false, false, now(), now(), '5f104ce3-af50-4173-9a19-df03bbc23219', 'flor20', 'Imagen', '$2y$10$SccF/uGAlvKp3i/2qB8M6u1dqMo5A/c0.avXbsNTDMGKppUmhn5Pm', 0, 0, null, now());
INSERT INTO main.user (user_administrator, locked, created_at, updated_at, user_id, user_name, profile_image, user_password, login_attempts, login_attempts_mfa, code_verification, created_code_verification) VALUES(false, false, now(), now(), '063bb6c3-5bd0-40c6-8f5f-5057aacb1af5', 'fabian21', 'Imagen', '$2y$10$SccF/uGAlvKp3i/2qB8M6u1dqMo5A/c0.avXbsNTDMGKppUmhn5Pm', 0, 0, null, now());
INSERT INTO main.user (user_administrator, locked, created_at, updated_at, user_id, user_name, profile_image, user_password, login_attempts, login_attempts_mfa, code_verification, created_code_verification) VALUES(false, false, now(), now(), '998c7d07-1737-467b-9cee-3d007bb46675', 'issela20', 'Imagen', '$2y$10$SccF/uGAlvKp3i/2qB8M6u1dqMo5A/c0.avXbsNTDMGKppUmhn5Pm', 0, 0, null, now());

INSERT INTO main.person (created_at, updated_at, person_document, user_id, person_phone_number, person_email, person_name, person_last_name, person_address) VALUES (now(), now(), '1003168607', '2f82e69e-887d-4360-ad94-151d060d3350', '3112884010', 'haroldmartinez@wposs.com', 'Harold', 'Martinez', 'Ocaña');

INSERT INTO main.person (created_at, updated_at, person_document, user_id, person_phone_number, person_email, person_name, person_last_name, person_address) VALUES(now(), now(), '1003168608', '5f104ce3-af50-4173-9a19-df03bbc23219', '3112884011', 'florbaza@wposs.com', 'Flor', 'Baza', 'Curumani');

INSERT INTO main.person (created_at, updated_at, person_document, user_id, person_phone_number, person_email, person_name, person_last_name, person_address) VALUES (now(), now(), '1003168609','063bb6c3-5bd0-40c6-8f5f-5057aacb1af5', '3112884012', 'fabiancarvajalino@wposs.com', 'Fabian', 'Carvajalino', 'Abrego');

INSERT INTO main.person (created_at, updated_at, person_document, user_id, person_phone_number, person_email, person_name, person_last_name, person_address) VALUES (now(), now(), '1003168610', '998c7d07-1737-467b-9cee-3d007bb46675', '3112884013', 'isselabeleño@wposs.com', 'Issela', 'Beleño', 'Rio de oro');



