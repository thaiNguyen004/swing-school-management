-- employee

-- 2 loại nhân viên {nhân viên 1, nhân viên 2}
-- nhân viên 2 sẽ có quyền thêm sửa xóa sinh vien, giáo viên, ...
-- nhân viên 1 sẽ có thêm một phần là quản lý nhân viên (frame) thêm, sửa, xóa, thay đổi role nhân viên (không thể xóa chính mình)

-- admin có mọi chức năng giống như nhân viên 1 (xóa tất cả nhân viên)

select * from UserInformations
select * from Employees

SELECT * from Employees where Employees.EmployeeID = 6 and Title = 1 and isDelete = 0

create or alter proc createEmployee
@employeeName NVARCHAR(50),
@sex int,
@dateOfBirth DATE,
@phoneNumber VARCHAR(10),
@Provice NVARCHAR(50),
@Title int,
@photo varbinary(max),
@lastModified DATE,
@descriptor NVARCHAR(255)
AS
INSERT INTO employees
(EmployeeName, Sex, DateOfBirth, PhoneNumber, Provice, Title, Photo, LastModified, Descriptor)
VALUES
(@employeeName, @sex, @dateOfBirth, @phoneNumber, @Provice, @Title, @photo, @lastModified, @descriptor)

CREATE OR ALTER TRIGGER InsertUserThenEmployee ON Employees
AFTER INSERT
AS
BEGIN
	UPDATE e 
	SET e.Email = CONCAT(dbo.prefixEmail(I.EmployeeName), RIGHT('0000' + CAST(I.EmployeeID AS VARCHAR(5)), 5), '@fpt.edu.vn'),
	e.ReportTo = CASE I.Title WHEN '1' THEN NULL ELSE (SELECT Employees.EmployeeID FROM Employees WHERE Employees.Title = 1) END
	FROM Employees AS e
	JOIN Inserted AS I ON e.EmployeeID = I.EmployeeID;

	INSERT INTO UserInformations 
	(Username, Password, StudentID, TeacherID, EmployeeID, Role, LastModified)
	SELECT 
		e.Email,
		SUBSTRING(e.Email, 1, charindex('@', e.Email) - 1),
		NULL,
		NULL,
		e.EmployeeID,
		2,
		GETDATE()
		FROM Employees AS e;
END


select * from Employees
create or alter proc changeRole
@employeeID INT
as
UPDATE Employees SET Title = 1, ReportTo = NULL
WHERE EmployeeID = @employeeID and isDelete = 0;
UPDATE Employees SET Title = 2, ReportTo = @employeeID
WHERE EmployeeID <> @employeeID and isDelete = 0;