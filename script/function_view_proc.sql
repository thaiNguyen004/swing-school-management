-- FUNCTIon -- VIEW -- STORAGE PROCEDURE

-- Cũ
SELECT * FROM DBO.getStudentByMSSV(11)
ALTER TABLE TeachingAssignments
ADD isTeaching INT DEFAULT 1

-- Lấy danh sách lớp học - môn học (thông tin của phân công)
CREATE OR ALTER function getAllTeachingassignment() 
RETURNS TABLE
as
RETURN 
SELECT 
	Teachingassignments.TeachingassignmentID,
	Teachingassignments.SubjectDetailID,
	Teachingassignments.ClassID,
	Teachingassignments.TeacherID,
	Teachingassignments.TeachInShift,
	Teachingassignments.ClassroomID,
	Teachingassignments.Semester,
	Teachingassignments.Season,
	Teachingassignments.Block,
	Teachingassignments.LastModified,
	Teachingassignments.Descriptor,
	Teachingassignments.isTeaching
    FROM Teachingassignments
SELECT * FROM dbo.getAllTeachingassignment()





-- Lấy tất cả các lớp ví dụ như SD18314
CREATE OR ALTER view v_classcodes
AS
SELECT 
        CASE
        WHEN SpecificallyMajors.SpecificallyMajorCode is null then concat(concat(Majors.MajorCode, Classes.Batch), right('000' + cast(Classes.ClassID  as NVARCHAR(3)), 3))
        ELSE concat(concat(SpecificallyMajors.SpecificallyMajorCode, Classes.Batch), right('000' + cast(Classes.ClassID  as NVARCHAR(3)), 3))
		END AS Classname,
		Classes.ClassID
        
        FROM Teachingassignments
        JOIN Classes on Teachingassignments.ClassID = Classes.ClassID 
        LEFT JOIN Majors on Majors.MajorID = Classes.MajorID
        LEFT JOIN SpecificallyMajors on SpecificallyMajors.SpecificallyMajorID = Classes.SpecificallyMajorID
		group by Classes.ClassID,
		SpecificallyMajors.SpecificallyMajorCode,
		Majors.MajorCode,
		Classes.Batch

SELECT * FROM v_classcodes


select * from classrooms


-- view sinh viên
CREATE OR ALTER VIEW v_student
as
SELECT distinct 
a.StudentID, 
a.FullName, 
a.Sex, 
a.Batch, 
a.DateOfBirth, 
a.Email, 
a.PhoneNumber, 
a.Provice, 
b.SpecificallyMajorName, 
a.Photo, 
a.LastModified, 
a.Descriptor 
FROM Students a
JOIN SpecificallyMajors b on b.SpecificallyMajorID = a.SpecificallyMajorID
WHERE a.isDelete = 0




-- lấy mã code của lớp (vd: SD18314) dựa vào id phân công
CREATE OR ALTER function getClassname(@TeachingassignmentID INT)
RETURNS TABLE
as
    RETURN
    SELECT 
        CASE
        WHEN SpecificallyMajors.SpecificallyMajorCode is null then concat(concat(Majors.MajorCode, Classes.Batch), right('000' + cast(Classes.ClassID as NVARCHAR(3)), 3))
        ELSE concat(concat(SpecificallyMajors.SpecificallyMajorCode, Classes.Batch), right('000' + cast(Classes.ClassID as NVARCHAR(3)), 3))
	END AS Classname
        
        FROM Teachingassignments
        JOIN Classes on Teachingassignments.ClassID = Classes.ClassID 
        LEFT JOIN Majors on Majors.MajorID = Classes.MajorID
        LEFT JOIN SpecificallyMajors on SpecificallyMajors.SpecificallyMajorID = Classes.SpecificallyMajorID
        WHERE Teachingassignments.TeachingassignmentID = @TeachingassignmentID

SELECT * FROM dbo.getClassname(3)





-- Lấy thông tin phân công dựa vào id giáo viên

CREATE OR ALTER function getTeachingassignment(@TeacherID INT) 
RETURNS TABLE 
as
RETURN 
    SELECT 
    Teachingassignments.TeachingassignmentID,
	Teachingassignments.SubjectDetailID,
	Teachingassignments.ClassID,
	Teachingassignments.TeacherID,
	Teachingassignments.TeachInShift,
	Teachingassignments.ClassroomID,
	Teachingassignments.Semester,
	Teachingassignments.Season,
	Teachingassignments.Block,
	Teachingassignments.LastModified,
	Teachingassignments.Descriptor,
	Teachingassignments.isTeaching
    FROM Teachingassignments
    JOIN Teachers on Teachingassignments.TeacherID = Teachers.TeacherID
    JOIN Classes on Teachingassignments.ClassID = Classes.ClassID
    LEFT JOIN Majors on Majors.MajorID = Classes.MajorID
	LEFT JOIN SpecificallyMajors on SpecificallyMajors.SpecificallyMajorID = Classes.SpecificallyMajorID 
	JOIN SubjectsDetail on Teachingassignments.subjectDetailID = SubjectsDetail.subjectDetailID
    JOIN Subjects on SubjectsDetail.subjectID = Subjects.subjectID
    JOIN Classrooms on Teachingassignments.ClassroomID = Classrooms.ClassroomID
    JOIN Buildings on Classrooms.BuildingID = Buildings.BuildingID
    WHERE Teachers.TeacherID = @TeacherID and TeachingAssignments.isTeaching = 1 AND Subjects.isDelete = 0

SELECT * FROM dbo.getTeachingassignment(1)

SELECT Subjects.SubjectName FROM Subjects WHERE Subjects.SubjectID = 61




 -- find
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
	Students.LastModified, 
	Students.Descriptor 
    FROM Students
	JOIN SpecificallyMajors on SpecificallyMajors.SpecificallyMajorID = Students.SpecificallyMajorID
	JOIN mssv on students.studentid = mssv.studentid
	WHERE mssv.studentcode LIKE '%00%' AND students.isDelete = 0

SELECT * FROM students





-- Lấy thông tin môn học từ id teachingassignment
CREATE OR ALTER function getSubjectByTasm (@TeachingasmID INT) 
RETURNS TABLE
as
RETURN 
SELECT
	a.SubjectID,
	a.SubjectDetailID,
	s.SubjectName,
	s.SubjectCode,
	s.Credit,
	s.TypeOfSubject,
	s.Furlough,
	s.LastModified,
	s.MajorID,
	s.Descriptor
	FROM SubjectsDetail a
	JOIN Teachingassignments on a.SubjectDetailID = Teachingassignments.SubjectDetailID 
	JOIN Subjects s on a.SubjectID = s.SubjectID
	WHERE Teachingassignments.TeachingassignmentID = @TeachingasmID


SELECT * FROM teachers