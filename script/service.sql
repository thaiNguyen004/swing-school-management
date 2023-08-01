-- trigger - proc - function  - Services

CREATE OR ALTER function getTeachingAssignmentByClassID (@ClassID INT)
RETURNS TABLE
as
RETURN
SELECT 
    t.TeachingassignmentID,
	t.SubjectDetailID,
	t.ClassID,
	t.TeacherID,
	t.TeachInShift,
	t.ClassroomID,
	t.Semester,
	t.Season,
	t.Block,
	t.LastModified,
	t.Descriptor,
	t.isTeaching
    FROM Teachingassignments t
	WHERE t.ClassID = @ClassID

select * from dbo.getTeachingAssignmentByClassID(1)


-- function này để tra cứu tất cả sinh viên đang học trong một lớp nào đó
CREATE OR ALTER function getStudentsParticipation(@TeachingassignmentID INT, @status INT)
RETURNS TABLE 
as
RETURN 
SELECT
		Students.StudentID, 
		Students.FullName, 
		Students.Sex, 
		Students.Batch, 
		Students.DateOfBirth,
		Students.Email, 
		Students.PhoneNumber,
		Students.Provice,
		SpecificallyMajors.SpecificallyMajorName,
		Students.Photo,
		Students.LastModified,
		Students.Descriptor
		FROM Teachingassignments
		JOIN Teachers on Teachingassignments.TeacherID = Teachers.TeacherID
		JOIN StudyingParticipations on StudyingParticipations.TeachingassignmentID = Teachingassignments.TeachingassignmentID
		JOIN Students on Students.StudentID = StudyingParticipations.StudentID
		JOIN SpecificallyMajors on SpecificallyMajors.SpecificallyMajorID = Students.SpecificallyMajorID
		WHERE Teachingassignments.TeachingassignmentID = @TeachingassignmentID AND StudyingParticipations.Status = @status



SELECT 
		t.StudentID, 
		t.FullName, 
		t.Sex, 
		t.Batch, 
		t.DateOfBirth,
		t.Email, 
		t.PhoneNumber, 
		t.Provice, 
		SpecificallyMajors.SpecificallyMajorName,
		t.Photo,
		t.LastModified,
		t.Descriptor,
		Subjects.SubjectName
		FROM Teachingassignments
		JOIN Subjects on Subjects.SubjectID = TeachingAssignments.SubjectID
		JOIN Teachers on Teachingassignments.TeacherID = Teachers.TeacherID
		JOIN StudyingParticipations on StudyingParticipations.TeachingassignmentID = Teachingassignments.TeachingassignmentID
		JOIN Students t on t.StudentID = StudyingParticipations.StudentID
		JOIN SpecificallyMajors on SpecificallyMajors.SpecificallyMajorID = t.SpecificallyMajorID
		WHERE Teachingassignments.TeachingassignmentID = 1 AND StudyingParticipations.Status = 1 




create or alter function getSpecificallyMajorByMajorID (@majorID int, @isDelete int) 
returns table
as
return 
select 
s.SpecificallyMajorID,
s.SpecificallyMajorName,
s.SpecificallyMajorCode,
s.MajorID,
s.LastModified,
s.Descriptor
from specificallymajors s
join Majors on s.MajorID = Majors.MajorID
where Majors.MajorID = @majorID and s.isDelete = @isDelete


select * from dbo.getSpecificallyMajorByMajorID(1, 0)


/*
create or alter function getSubjectByMajorID(@majorID int, @isDelete int)
returns table
as
return
select 
Subjects.SubjectID,
Subjects.SubjectName,
Subjects.SubjectCode,
Subjects.Credit,
Subjects.TypeOfSubject,
Subjects.FurLough,
Subjects.LastModified,
Subjects.MajorID,
Subjects.Descriptor

from Subjects
join Majors on Subjects.MajorID = Majors.MajorID
where Majors.MajorID = @majorID and Majors.isDelete = @isDelete

select * from dbo.getSubjectByMajorID(1, 0)

*/

select * 
from classes
join subjects on classes.MajorID = Subjects.MajorID
where Subjects.MajorID = 1

select * from SubjectsDetail

SELECT * FROM SUBJECTS
SELECT * FROM Classes 
select * from specificallyMajors


create or alter function getSubjectDetailBySpecificallyMajor(@specificallyMajorID int, @isDelete int)
returns table
as
return
select 
	b.SubjectID,
	a.SubjectDetailID,
	b.SubjectName,
	b.SubjectCode, 
	b.Credit,
	b.TypeOfSubject,
	b.FurLough,
	b.MajorID,
	a.SpecificallyMajorID,
	b.LastModified,
	b.Descriptor

	from SubjectsDetail a
	join Subjects b on a.SubjectID = b.SubjectID
	join SpecificallyMajors c on c.SpecificallyMajorID = a.SpecificallyMajorID
	where c.SpecificallyMajorID = @specificallyMajorID and b.isDelete = @isDelete 



select * from dbo.getSubjectDetailBySpecificallyMajor(1,0)
select * from SubjectsDetail

select * from classes where classes.SpecificallyMajorID is null
select * from SpecificallyMajors
select * from Majors


CREATE OR ALTER FUNCTION getClassCodeBySubject (@specificallyMajorID int, @isDelete int)
returns table 
as
return 
select 
	c.ClassID,
	concat(concat(s.SpecificallyMajorCode, c.Batch), right('000' + cast(c.classID as varchar(3)), 3)) as Classcode,
	c.MajorID,
	c.SpecificallyMajorID,
	c.Batch,
	c.LastModified,
	c.Descriptor
	from Classes c
	left join Majors m on c.MajorID = m.MajorID
	left join SpecificallyMajors s on c.SpecificallyMajorID = s.SpecificallyMajorID
	where 
	c.SpecificallyMajorID = @specificallyMajorID AND c.isDelete = @isDelete;

select * from dbo.getClassCodeBySubject(4, 0)
select * from classes


-- Proc (KHÓ) Select tất cả các lớp khả dụng của môn XXX -> chọn ra tất cả các lớp mà chưa từng được ghép với môn XXX
CREATE OR ALTER FUNCTION getClassFromSubjectDetailAvailable(@SubjectDetailID INT, @SpecificallyMajorID INT, @isDelete INT)
RETURNS TABLE 
AS
RETURN
SELECT 
c.ClassID,
concat(concat(s.SpecificallyMajorCode, c.Batch), right('000' + cast(c.classID as varchar(3)), 3)) as Classcode,
c.MajorID,
c.SpecificallyMajorID,
c.Batch,
c.LastModified,
c.Descriptor

FROM Teachingassignments t
RIGHT JOIN Classes c on t.ClassID = c.ClassID
JOIN SpecificallyMajors s on c.SpecificallyMajorID = s.SpecificallyMajorID
WHERE 
(
	t.TeachingassignmentID IS NULL 
	OR 
	t.ClassID NOT IN (
		SELECT t.ClassID
		FROM Teachingassignments t
		RIGHT JOIN Classes c on t.ClassID = c.ClassID
		WHERE (t.SubjectDetailID = @SubjectDetailID)
	)
) AND c.SpecificallyMajorID = @SpecificallyMajorID AND c.isDelete = @isDelete;
select * from dbo.getClassFromSubjectDetailAvailable(8, 2, 0)
select * from SubjectsDetail
select * from teachingAssignments



CREATE OR ALTER FUNCTION getClassroomFromShift(@shift INT)
RETURNS TABLE
AS
RETURN
select 
	c.ClassroomID,
	c.NumberOfFloor,
	c.NumberOfRoom,
	c.BuildingID,
	c.Status,
	c.LastModified,
	c.Descriptor
	FROM Teachingassignments t
	RIGHT JOIN Classrooms c ON c.ClassroomID = t.ClassroomID
	WHERE t.TeachingassignmentID IS NULL OR
	t.ClassroomID NOT IN (
		SELECT t.ClassroomID
		FROM Teachingassignments t
		RIGHT JOIN Classrooms c on t.ClassroomID = c.ClassroomID
		WHERE t.TeachInShift = @shift)

select * from Classrooms

select * from Classrooms


CREATE OR ALTER PROC createTeachingAssignment 
@subjectDetailID INT,
@classID INT,
@teacherID INT,
@teachInShift INT,
@classroomID INT,
@semester INT,
@season VARCHAR(50),
@block INT,
@lastModified DATE,
@descriptor NVARCHAR(255)
AS
INSERT INTO TeachingAssignments 
(SubjectDetailID, ClassID, TeacherID, TeachInShift, ClassroomID, Semester, Season, Block, LastModified, Descriptor)
VALUES
(@subjectDetailID, @classID, @teacherID, @teachInShift, @classroomID, @semester, @season, @block, @lastModified, @descriptor)

select * from teachingassignments


select * from dbo.getClassname(7)
select SubjectName from Subjects where SubjectID = 53

CREATE OR ALTER FUNCTION getSubjectFromSubjectDetailID (@subjectDetailID INT)
RETURNS TABLE
AS
RETURN
SELECT 
	s1.SubjectID,
	s2.SubjectDetailID,
	s1.SubjectName, 
	s1.SubjectCode,
	s1.Credit,
	s1.TypeOfSubject,
	s1.FurLough,
	s1.LastModified,
	s1.MajorID,
	s2.SpecificallyMajorID,
	s1.Descriptor
	FROM Subjects s1
	JOIN SubjectsDetail s2 ON s1.SubjectID = s2.SubjectID
	WHERE s2.SubjectDetailID = @subjectDetailID


CREATE OR ALTER FUNCTION getTeachingAssignmentsFromSubjectDetailID (@subjectDetailID INT)
RETURNS TABLE
AS
RETURN
SELECT * FROM TeachingAssignments t
	WHERE t.SubjectDetailID = @subjectDetailID

select * from dbo.getTeachingAssignmentsFromSubjectDetailID(5)

select * from subjects
select * from specificallyMajors
select * from classes

CREATE OR ALTER PROC  updateClass 
@classID INT ,
@batch INT,
@descriptor NVARCHAR(255)
AS
BEGIN
UPDATE	Classes SET Descriptor = @descriptor,
		Classes.Batch = @batch,
		Classes.LastModified = Getdate()
		WHERE ClassID = @classID
END

select * from SubjectsDetail
CREATE OR ALTER PROC createSubject
@SubjectName NVARCHAR(50),
@SubjectCode VARCHAR(10),
@Credit INT,
@TypeOfSubject INT,
@Furlough INT,
@LastModified DATE,
@MajorID INT,
@Descriptor NVARCHAR(255)
AS
BEGIN
	INSERT INTO Subjects 
	(SubjectName, SubjectCode, Credit, TypeOfSubject, FurLough, LastModified, MajorID, Descriptor)
	VALUES
	(@SubjectName, @SubjectCode, @Credit, @TypeOfSubject, @Furlough, @LastModified, @MajorID, @Descriptor);
END


CREATE OR ALTER TRIGGER createSubjectDetail ON Subjects
AFTER INSERT
AS
BEGIN
	DECLARE @SubjectID INT;
	SELECT @SubjectID = SubjectID from inserted;

	INSERT INTO SubjectsDetail
	(SubjectID, SpecificallyMajorID, LastModified, Descriptor)
	VALUES
	(@SubjectID, NULL, GETDATE(), NULL)
END


select * from subjects
select * from SubjectsDetail

CREATE OR ALTER FUNCTION getSubjectHaveNotDone()
RETURNS TABLE
AS
RETURN 
SELECT 
	s1.SubjectID,
	s2.SubjectDetailID,
	s1.SubjectName, 
	s1.SubjectCode,
	s1.Credit,
	s1.TypeOfSubject,
	s1.FurLough,
	s1.LastModified,
	s1.MajorID,
	s1.Descriptor

	FROM SubjectsDetail s2
	JOIN Subjects s1 ON s1.SubjectID = s2.SubjectID
	WHERE s2.SpecificallyMajorID IS NULL

select * from dbo.getSubjectHaveNotDone()

CREATE OR ALTER PROC updateSubjectHaveNotDone
@SubjectDetailID INT,
@SpecificallyMajorID INT
AS
BEGIN
	UPDATE SubjectsDetail SET SubjectsDetail.SpecificallyMajorID = @SpecificallyMajorID WHERE SubjectsDetail.SubjectDetailID = @SubjectDetailID
END


CREATE OR ALTER PROC createClasses
@majorID INT,
@specificallyMajorID INT,
@batch INT,
@lastModified DATE,
@descriptor NVARCHAR(255)
AS
BEGIN
	INSERT INTO Classes
	(MajorID, SpecificallyMajorID, Batch, LastModified, Descriptor)
	VALUES
	(@majorID, @specificallyMajorID, @batch, @lastModified, @descriptor)
END

select * from classes
select * from teachingAssignments
select * from StudyingParticipations

CREATE OR ALTER FUNCTION getSubjectByStudyingParticipations(@studentID INT)
RETURNS TABLE
AS
RETURN
select 
	res.SubjectID,
	sd.SubjectDetailID,
	res.SubjectName, 
	res.SubjectCode,
	res.Credit,
	res.TypeOfSubject,
	res.FurLough,
	res.LastModified,
	res.MajorID,
	res.Descriptor

	from studyingParticipations s 
	join Students on s.StudentID = Students.StudentID
	join TeachingAssignments t on s.TeachingAssignmentID = t.TeachingAssignmentID
	join SubjectsDetail sd on t.subjectDetailID = sd.subjectDetailID
	join Subjects res on res.subjectID = sd.subjectID
	where Students.StudentID = @studentID

select * from teachingassignments
select * from studyingparticipations

create or alter function getGradeForSubjectDetailID(@subjectDetailID int, @studentID int)
returns table
as
return
select 
	grades.Lab1, 
	grades.Lab2,
	grades.Lab3,
	grades.Lab4,
	grades.Lab5,
	grades.Lab6,
	grades.Lab7,
	grades.Lab8,
	grades.Quiz1,
	grades.Quiz2,
	grades.Quiz3,
	grades.Quiz4,
	grades.Quiz5,
	grades.Quiz6,
	grades.Quiz7,
	grades.Quiz8,
	grades.assignment1,
	grades.assignment2,
	grades.assignmentFinal
from grades
join SubjectsDetail on Grades.SubjectDetailID = SubjectsDetail.SubjectDetailID
where grades.StudentID = @studentID and grades.SubjectDetailID = @subjectDetailID
select * from grades
select * from dbo.getGradeForSubjectDetailID(7,1)

update students set Descriptor = 'a' where students.studentid = 1


SELECT * FROM Students
JOIN UserInformations ON Students.email = UserInformations.username 
WHERE UserInformations.username = 'nguyenntph00001@fpt.edu.vn'
select * from students

select * from UserInformations

select * from dbo.getClassCodeBySubject(1, 0)
select * from students
select * from studyingParticipations
select * from teachingassignments
select * from subjectsdetail
select * from subjects
select * from grades

select * from dbo.getStudentsParticipation(1, 1)


CREATE OR ALTER PROC updateGrade
@subjectDetailID INT,
@studentID INT,
@lab1 FLOAT,
@lab2 FLOAT,
@lab3 FLOAT,
@lab4 FLOAT,
@lab5 FLOAT,
@lab6 FLOAT,
@lab7 FLOAT,
@lab8 FLOAT,
@quiz1 FLOAT,
@quiz2 FLOAT,
@quiz3 FLOAT,
@quiz4 FLOAT,
@quiz5 FLOAT,
@quiz6 FLOAT,
@quiz7 FLOAT,
@quiz8 FLOAT,
@asm1 FLOAT,
@asm2 FLOAT,
@asmfinal FLOAT
AS
update Grades 
SET 
Grades.Lab1= @lab1,
Grades.Lab2 = @lab2,
Grades.Lab3 = @lab3,
Grades.Lab4 = @lab4,
Grades.Lab5 = @lab5,
Grades.Lab6 = @lab6,
Grades.Lab7 = @lab7,
Grades.Lab8 = @lab8,
Grades.quiz1 = @quiz1,
Grades.quiz2 = @quiz2,
Grades.quiz3 = @quiz3,
Grades.quiz4 = @quiz4,
Grades.quiz5 = @quiz5,
Grades.quiz6 = @quiz6,
Grades.quiz7 = @quiz7,
Grades.quiz8 = @quiz8,
Grades.assignment1 = @asm1,
Grades.assignment2 = @asm2,
Grades.assignmentFinal = @asmFinal
where Grades.SubjectDetailID = @subjectDetailID and Grades.StudentID = @studentID


EXEC updateGrade  6, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1

CREATE OR ALTER FUNCTION getTeacherBySubjectAndStudent(@subjectDetailID INT, @studentID INT)
RETURNS TABLE
AS
RETURN 
	SELECT Teachers.TeacherName
	FROM TeachingAssignments t
	JOIN StudyingParticipations sp ON t.teachingAssignmentID = sp.teachingAssignmentID
	JOIN SubjectsDetail s ON t.subjectDetailID = s.subjectDetailID
	JOIN Teachers ON t.TeacherID = Teachers.TeacherID
	WHERE sp.studentID = @studentID and s.subjectDetailID = @subjectDetailID
	select * from Teachingassignments

	select * from Subjects
	select * from SubjectsDetail
	select * from StudyingParticipations
	
	select * from dbo.getSubjectByStudyingParticipations(1)
	select * from grades


delete from grades
delete from StudyingParticipations
delete from Teachingassignments
delete from subjectsdetail
delete from subjects
delete from Attendances
delete from UserInformations where UserID <> 1
delete from MSSV
delete from students
delete from MSGV
delete from Teachers
delete from Classes
delete from specificallyMajors
delete from Classrooms
delete from Buildings
delete from majors
delete from employees

SELECT * FROM INFORMATION_SCHEMA.TABLES;
INSERT INTO Teachers 
(TeacherName, Sex, DateOfBirth, PhoneNumber, Provice, MajorID, TeacherManagerID, Photo, LastModified, Descriptor)
VALUES
('admin', '1', '1970-01-01', '0777777777', 'Mặt Trăng', '1', null, null, getdate(), null)

SELECT SpecificallyMajors.SpecificallyMajorID, 
                           SpecificallyMajors.SpecificallyMajorName FROM SpecificallyMajors


create or alter proc createSpecificallyMajor
@majorID int,
@SpecificallyMajorName nvarchar(50),
@SpecificallyMajorCode varchar(10),
@Descriptor nvarchar(255)
as
INSERT INTO SpecificallyMajors
(SpecificallyMajorName, SpecificallyMajorCode, MajorID, LastModified, Descriptor)
values
(@SpecificallyMajorName, @SpecificallyMajorCode, @majorID, getdate(), @Descriptor)

SELECT SpecificallyMajors.SpecificallyMajorID, 
                           SpecificallyMajors.SpecificallyMajorName FROM SpecificallyMajors

select * from v_classcodes

select * from classrooms


create or alter proc createClassroom 
@NumberOfFloor int,
@NumberOfRoom int,
@BuildingID int,
@status int,
@descriptor nvarchar(255)
as
insert into Classrooms
(NumberOfFloor, NumberOfRoom, BuildingID, Status, LastModified, Descriptor)
values
(@NumberOfFloor, @NumberOfRoom, @BuildingID, @status, getdate(), @descriptor)



create or alter view v_Building
as
select * from buildings

select * from SpecificallyMajors


select * from dbo.getClassroomFromShift(1)

Create or alter proc getClassroomCode 
@classroomID int,
@classroomCode varchar(10) output
as 
select @classroomCode = 
	concat(b.BuildingCode, right('000' + cast(c.ClassroomID as varchar(3)), 3))
	from Classrooms c
	join Buildings b on c.BuildingID = b.BuildingID
	where c.ClassroomID  = @classroomID

	declare @out varchar(5)
	execute getClassroomCode 201, @out output
	print @out

	
select * from Teachingassignments
select * from StudyingParticipations
select * from subjectsdetail
select * from grades