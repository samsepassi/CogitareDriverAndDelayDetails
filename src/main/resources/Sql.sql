CREATE TABLE train_delay_details (Journey_id INTEGER NOT NULL, Departure_lateness INTEGER, Departure_time DATE, Station VARCHAR(255), Train_id VARCHAR(255), PRIMARY KEY (Journey_id));
CREATE TABLE train_driver_details (Journey_id INTEGER NOT NULL, Journey_Status VARCHAR(255), Driver_Name VARCHAR(255), End_Station VARCHAR(255), Start_Station VARCHAR(255), Train_id VARCHAR(255), PRIMARY KEY (Journey_id));
CREATE TABLE drivers (Driver_Name VARCHAR(255) NOT NULL, PRIMARY KEY (Driver_Name));
CREATE TABLE trains (TrainID VARCHAR(255) NOT NULL, PRIMARY KEY (TrainID));
CREATE TABLE stations (StationID VARCHAR(255) NOT NULL, PRIMARY KEY (StationID));
ALTER TABLE train_delay_details ADD CONSTRAINT FK_train_delay_details_Train_id FOREIGN KEY (Train_id) REFERENCES trains (TrainID);
ALTER TABLE train_delay_details ADD CONSTRAINT FK_train_delay_details_Station FOREIGN KEY (Station) REFERENCES stations (StationID);
ALTER TABLE train_driver_details ADD CONSTRAINT FK_train_driver_details_End_Station FOREIGN KEY (End_Station) REFERENCES stations (StationID);
ALTER TABLE train_driver_details ADD CONSTRAINT FK_train_driver_details_Train_id FOREIGN KEY (Train_id) REFERENCES trains (TrainID);
ALTER TABLE train_driver_details ADD CONSTRAINT FK_train_driver_details_Driver_Name FOREIGN KEY (Driver_Name) REFERENCES drivers (Driver_Name);
ALTER TABLE train_driver_details ADD CONSTRAINT FK_train_driver_details_Start_Station FOREIGN KEY (Start_Station) REFERENCES stations (StationID);