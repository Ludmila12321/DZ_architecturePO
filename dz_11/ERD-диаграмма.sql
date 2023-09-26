CREATE TABLE Users (
  id BIGINT NOT NULL AUTO_INCREMENT,
  email VARCHAR NOT NULL,
  password_hash VARCHAR NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (email)
);

CREATE TABLE Rooms (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR NOT NULL,
  area INT NOT NULL,
  robot_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (robot_id) REFERENCES Devices(id)
);

CREATE TABLE Schedules (
  id BIGINT NOT NULL AUTO_INCREMENT,
  day_of_week ENUM NOT NULL,
  start_time DATETIME NOT NULL,
  end_time DATETIME NOT NULL,
  room_id BIGINT NOT NULL,
  robot_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (room_id) REFERENCES Rooms(id),
  FOREIGN KEY (robot_id) REFERENCES Devices(id)
);

CREATE TABLE Devices (
  id BIGINT NOT NULL AUTO_INCREMENT,
  robot_serial_number VARCHAR NOT NULL,
  robot_model VARCHAR NOT NULL,
  filter_counter INT NOT NULL,
  brush_counter INT NOT NULL,
  sensor_dirt_level INT NOT NULL,
  last_cleaning_mode ENUM NOT NULL,
  battery_level INT NOT NULL,
  user_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (robot_serial_number),
  FOREIGN KEY (user_id) REFERENCES Users(id)
);

CREATE TABLE Cleaning_Logs (
  id BIGINT NOT NULL,
  device_id BIGINT NOT NULL,
  start_time DATETIME NOT NULL,
  end_time DATETIME NOT NULL,
  cleaned_area INT NOT NULL,
  room_id BIGINT NOT NULL,
  cleaning_mode ENUM NOT NULL,
  cleaning_type ENUM NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (device_id) REFERENCES Devices(id),
  FOREIGN KEY (room_id) REFERENCES Rooms(id)
);