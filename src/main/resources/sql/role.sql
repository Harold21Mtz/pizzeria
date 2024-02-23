INSERT INTO main.role (is_active, created_at, updated_at, role_id, role_name) values (true, now(), now(), 'e50411ea-ab77-453c-a75a-3b085f9eaef2', 'Administrator');

INSERT INTO main.role (is_active, created_at, updated_at, role_id, role_name) values (true, now(), now(), '2c85664b-0ce8-4cc1-9164-8ede2e47865e', 'Customer');

-----------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO main.user_role(role_id, user_id) values ('e50411ea-ab77-453c-a75a-3b085f9eaef2', '2f82e69e-887d-4360-ad94-151d060d3350');

INSERT INTO main.user_role(role_id, user_id) values ('2c85664b-0ce8-4cc1-9164-8ede2e47865e', '5f104ce3-af50-4173-9a19-df03bbc23219');

INSERT INTO main.user_role(role_id, user_id) values ('2c85664b-0ce8-4cc1-9164-8ede2e47865e', '063bb6c3-5bd0-40c6-8f5f-5057aacb1af5');

INSERT INTO main.user_role(role_id, user_id) values ('2c85664b-0ce8-4cc1-9164-8ede2e47865e', '998c7d07-1737-467b-9cee-3d007bb46675');