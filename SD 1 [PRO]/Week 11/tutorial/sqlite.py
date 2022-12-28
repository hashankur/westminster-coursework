import sqlite3

db = sqlite3.connect("contact.db")
cursor = db.cursor()

# Create database
sql = """
    CREATE TABLE IF NOT EXISTS contacts (
    contact_id integer PRIMARY KEY,
    first_name text,
    last_name text,
    email text,
    phone text);"""

cursor.execute(sql)
db.commit()

# Add records
sql = """
INSERT INTO contacts (first_name, last_name, email, phone)
VALUES ('Boris', 'Johnson', 'bj@number10.com', '12345678');
"""

cursor.execute(sql)
db.commit()
