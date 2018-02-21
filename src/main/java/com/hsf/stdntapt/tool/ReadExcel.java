package com.hsf.stdntapt.tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.hsf.stdntapt.entity.Class;
import com.hsf.stdntapt.entity.College;
import com.hsf.stdntapt.entity.Consellor;
import com.hsf.stdntapt.entity.Repairman;
import com.hsf.stdntapt.entity.SpeYears;
import com.hsf.stdntapt.entity.Speciality;
import com.hsf.stdntapt.entity.Staff;
import com.hsf.stdntapt.entity.Student;

public class ReadExcel {
	// 总行数
	private int totalRows = 0;
	// 总条数
	private int totalCells = 0;
	// 错误信息接收器
	private String errorMsg;

	// 构造方法
	public ReadExcel() {
	}

	// 获取总行数
	public int getTotalRows() {
		return totalRows;
	}

	// 获取总列数
	public int getTotalCells() {
		return totalCells;
	}

	// 获取错误信息
	public String getErrorInfo() {
		return errorMsg;
	}

	/**
	 * 验证EXCEL文件
	 *
	 * @param filePath
	 * @return
	 */
	public boolean validateExcel(String filePath) {
		if (filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath))) {
			errorMsg = "文件名不是excel格式";
			return false;
		}
		return true;
	}

	public Workbook checkfile(String fileName, MultipartFile Mfile, InputStream is) {
		try {
			// 验证文件名是否合格
			if (!validateExcel(fileName)) {
				return null;
			}
			// 根据文件名判断文件是2003版本还是2007版本
			boolean isExcel2003 = true;
			if (WDWUtil.isExcel2007(fileName)) {
				isExcel2003 = false;
			}

			/** 根据版本选择创建Workbook的方式 */
			Workbook wb = null;
			// 当excel是2003时
			if (isExcel2003) {
				wb = new HSSFWorkbook(is);
			} else {// 当excel是2007时
				wb = new XSSFWorkbook(is);
			}
			return wb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读学院EXCEL文件
	 *
	 * @param fileName
	 * @return
	 */
	public List<College> getCollegeExcelInfo(String fileName, MultipartFile Mfile) {
		List<College> list = new ArrayList<College>();
		// 初始化输入流
		InputStream is = null;
		try {
			// 根据新建的文件实例化输入流
			is = Mfile.getInputStream();

			Workbook wb = checkfile(fileName, Mfile, is);

			if (wb != null) {
				// 读取Excel里的信息
				list = readCollegeExcelValue(wb);
			}

			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	/**
	 * 读学制EXCEL文件
	 *
	 * @param fileName
	 * @return
	 */
	public List<SpeYears> getSpeYearsExcelInfo(String fileName, MultipartFile Mfile) {
		List<SpeYears> list = new ArrayList<SpeYears>();
		// 初始化输入流
		InputStream is = null;
		try {
			// 根据新建的文件实例化输入流
			is = Mfile.getInputStream();

			Workbook wb = checkfile(fileName, Mfile, is);

			if (wb != null) {
				// 读取Excel里的信息
				list = readSpeYearsExcelValue(wb);
			}

			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	/**
	 * 读专业EXCEL文件
	 *
	 * @param fileName
	 * @return
	 */
	public List<Speciality> getSpecialityExcelInfo(String fileName, MultipartFile Mfile) {
		List<Speciality> list = new ArrayList<Speciality>();
		// 初始化输入流
		InputStream is = null;
		try {
			// 根据新建的文件实例化输入流
			is = Mfile.getInputStream();

			Workbook wb = checkfile(fileName, Mfile, is);

			if (wb != null) {
				// 读取Excel里的信息
				list = readSpecialityExcelValue(wb);
			}

			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	/**
	 * 读辅导员EXCEL文件
	 *
	 * @param fileName
	 * @return
	 */
	public List<Consellor> getConsellorExcelInfo(String fileName, MultipartFile Mfile) {
		List<Consellor> list = new ArrayList<Consellor>();
		// 初始化输入流
		InputStream is = null;
		try {
			// 根据新建的文件实例化输入流
			is = Mfile.getInputStream();

			Workbook wb = checkfile(fileName, Mfile, is);

			if (wb != null) {
				// 读取Excel里的信息
				list = readConsellorExcelValue(wb);
			}

			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	/**
	 * 读班级EXCEL文件
	 *
	 * @param fileName
	 * @return
	 */
	public List<Class> getClassExcelInfo(String fileName, MultipartFile Mfile) {
		List<Class> list = new ArrayList<Class>();
		// 初始化输入流
		InputStream is = null;
		try {
			// 根据新建的文件实例化输入流
			is = Mfile.getInputStream();

			Workbook wb = checkfile(fileName, Mfile, is);

			if (wb != null) {
				// 读取Excel里的信息
				list = readClassExcelValue(wb);
			}

			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * 读学生EXCEL文件
	 *
	 * @param fileName
	 * @return
	 */
	public List<Student> getStudentExcelInfo(String fileName, MultipartFile Mfile) {
		List<Student> list = new ArrayList<Student>();
		// 初始化输入流
		InputStream is = null;
		try {
			// 根据新建的文件实例化输入流
			is = Mfile.getInputStream();

			Workbook wb = checkfile(fileName, Mfile, is);

			if (wb != null) {
				// 读取Excel里的信息
				list = readStudentExcelValue(wb);
			}

			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * 读工作人员EXCEL文件
	 *
	 * @param fileName
	 * @return
	 */
	public List<Staff> getStaffExcelInfo(String fileName, MultipartFile Mfile) {
		List<Staff> list = new ArrayList<Staff>();
		// 初始化输入流
		InputStream is = null;
		try {
			// 根据新建的文件实例化输入流
			is = Mfile.getInputStream();

			Workbook wb = checkfile(fileName, Mfile, is);

			if (wb != null) {
				// 读取Excel里的信息
				list = readStaffExcelValue(wb);
			}

			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * 读维修人员EXCEL文件
	 *
	 * @param fileName
	 * @return
	 */
	public List<Repairman> getRepairmanExcelInfo(String fileName, MultipartFile Mfile) {
		List<Repairman> list = new ArrayList<Repairman>();
		// 初始化输入流
		InputStream is = null;
		try {
			// 根据新建的文件实例化输入流
			is = Mfile.getInputStream();

			Workbook wb = checkfile(fileName, Mfile, is);

			if (wb != null) {
				// 读取Excel里的信息
				list = readRepairmanExcelValue(wb);
			}

			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * 读取学院Excel里面信息
	 *
	 * @param wb
	 * @return
	 */
	private List<College> readCollegeExcelValue(Workbook wb) {
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);

		// 得到Excel的行数
		this.totalRows = sheet.getPhysicalNumberOfRows();

		// 得到Excel的列数(前提是有行数)
		if (totalRows >= 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<College> collegeList = new ArrayList<College>();
		// 循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			College college = new College();
			Row row = sheet.getRow(r).getCell(1).getRow();
			if (row == null)
				continue;
			// 循环Excel的列,获取相关信息
			for (int c = 0; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {
					if (c == 0) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						college.setCollegeId(Integer.parseInt(cell.getStringCellValue()));
					} else if (c == 1) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						college.setCollegeName(cell.getStringCellValue());
					}
				}
			}
			// 添加
			collegeList.add(college);
		}
		return collegeList;
	}

	/**
	 * 读取学制Excel里面信息
	 *
	 * @param wb
	 * @return
	 */
	private List<SpeYears> readSpeYearsExcelValue(Workbook wb) {
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);

		// 得到Excel的行数
		this.totalRows = sheet.getPhysicalNumberOfRows();

		// 得到Excel的列数(前提是有行数)
		if (totalRows >= 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<SpeYears> speYearsList = new ArrayList<SpeYears>();
		// 循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			SpeYears speYears = new SpeYears();
			Row row = sheet.getRow(r).getCell(1).getRow();
			if (row == null)
				continue;
			// 循环Excel的列,获取相关信息
			for (int c = 0; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {
					if (c == 0) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						speYears.setSpeYearsId(Integer.parseInt(cell.getStringCellValue()));
					} else if (c == 1) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						speYears.setSpeYearsName(cell.getStringCellValue());
					} else if (c == 2) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						speYears.setSpeYearsLength(Integer.parseInt(cell.getStringCellValue()));
					}
				}
			}
			// 添加
			speYearsList.add(speYears);
		}
		return speYearsList;
	}

	/**
	 * 读取专业Excel里面信息
	 *
	 * @param wb
	 * @return
	 */
	private List<Speciality> readSpecialityExcelValue(Workbook wb) {
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);

		// 得到Excel的行数
		this.totalRows = sheet.getPhysicalNumberOfRows();

		// 得到Excel的列数(前提是有行数)
		if (totalRows >= 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<Speciality> specialityList = new ArrayList<Speciality>();
		// 循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			Speciality speciality = new Speciality();
			Row row = sheet.getRow(r).getCell(1).getRow();
			if (row == null)
				continue;
			// 循环Excel的列,获取相关信息
			for (int c = 0; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {
					if (c == 0) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						speciality.setSpeciId(Integer.parseInt(cell.getStringCellValue()));
					} else if (c == 1) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						speciality.setSpeciName(cell.getStringCellValue());
					} else if (c == 2) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						speciality.setCollegeId(Integer.parseInt(cell.getStringCellValue()));
					} else if (c == 3) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						speciality.setSpeYearsId(Integer.parseInt(cell.getStringCellValue()));
					}
				}
			}
			// 添加
			specialityList.add(speciality);
		}
		return specialityList;
	}

	/**
	 * 读取辅导员Excel里面信息
	 *
	 * @param wb
	 * @return
	 */
	private List<Consellor> readConsellorExcelValue(Workbook wb) {
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);

		// 得到Excel的行数
		this.totalRows = sheet.getPhysicalNumberOfRows();

		// 得到Excel的列数(前提是有行数)
		if (totalRows >= 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<Consellor> consellorList = new ArrayList<Consellor>();
		// 循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			Consellor consellor = new Consellor();
			Row row = sheet.getRow(r).getCell(1).getRow();
			if (row == null)
				continue;
			// 循环Excel的列,获取相关信息
			for (int c = 0; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {
					if (c == 0) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						consellor.setConsellId(Integer.parseInt(cell.getStringCellValue()));
					} else if (c == 1) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						consellor.setConsellName(cell.getStringCellValue());
					} else if (c == 2) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						consellor.setConsellSex(Integer.parseInt(cell.getStringCellValue()));
					} else if (c == 3) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						consellor.setConsellTel(cell.getStringCellValue());
					}
				}
			}
			// 添加
			consellorList.add(consellor);
		}
		return consellorList;
	}

	/**
	 * 读取班级Excel里面信息
	 *
	 * @param wb
	 * @return
	 */
	private List<Class> readClassExcelValue(Workbook wb) {
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);

		// 得到Excel的行数
		this.totalRows = sheet.getPhysicalNumberOfRows();

		// 得到Excel的列数(前提是有行数)
		if (totalRows >= 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<Class> classList = new ArrayList<Class>();
		// 循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			Class classes = new Class();
			Row row = sheet.getRow(r).getCell(1).getRow();
			if (row == null)
				continue;
			// 循环Excel的列,获取相关信息
			for (int c = 0; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {
					if (c == 0) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						classes.setClassId(Integer.parseInt(cell.getStringCellValue()));
					} else if (c == 1) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						classes.setClassName(cell.getStringCellValue());
					} else if (c == 2) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						classes.setSpeciId(Integer.parseInt(cell.getStringCellValue()));
					} else if (c == 3) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						classes.setConsellId(Integer.parseInt(cell.getStringCellValue()));
					}
				}
			}
			// 添加
			classList.add(classes);
		}
		return classList;
	}

	/**
	 * 读取学生Excel里面信息
	 *
	 * @param wb
	 * @return
	 */
	private List<Student> readStudentExcelValue(Workbook wb) {
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);

		// 得到Excel的行数
		this.totalRows = sheet.getPhysicalNumberOfRows();

		// 得到Excel的列数(前提是有行数)
		if (totalRows >= 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<Student> studentList = new ArrayList<Student>();
		// 循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			Student students = new Student();
			Row row = sheet.getRow(r).getCell(1).getRow();
			if (row == null)
				continue;
			// 循环Excel的列,获取相关信息
			for (int c = 0; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {
					if (c == 0) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						students.setStdId(Integer.parseInt(cell.getStringCellValue()));
					} else if (c == 1) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						students.setStdName(cell.getStringCellValue());
					} else if (c == 2) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						students.setStdSex(Integer.parseInt(cell.getStringCellValue()));
					} else if (c == 3) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						students.setStdTel(cell.getStringCellValue());
					} else if (c == 4) {
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							students.setEnterTime(cell.getDateCellValue());
						}
					} else if (c == 5) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						students.setParty(Boolean.parseBoolean(cell.getStringCellValue()));
					} else if (c == 6) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						students.setClassId(Integer.parseInt(cell.getStringCellValue()));
					}
				}
			}
			// 添加
			studentList.add(students);
		}
		return studentList;
	}

	/**
	 * 读取工作人员Excel里面信息
	 *
	 * @param wb
	 * @return
	 */
	private List<Staff> readStaffExcelValue(Workbook wb) {
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);

		// 得到Excel的行数
		this.totalRows = sheet.getPhysicalNumberOfRows();

		// 得到Excel的列数(前提是有行数)
		if (totalRows >= 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<Staff> staffList = new ArrayList<Staff>();
		// 循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			Staff staffs = new Staff();
			Row row = sheet.getRow(r).getCell(1).getRow();
			if (row == null)
				continue;
			// 循环Excel的列,获取相关信息
			for (int c = 0; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {
					if (c == 0) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						staffs.setStaffId(Integer.parseInt(cell.getStringCellValue()));
					} else if (c == 1) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						staffs.setStaffName(cell.getStringCellValue());
					} else if (c == 2) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						staffs.setStaffSex(Integer.parseInt(cell.getStringCellValue()));
					} else if (c == 3) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						staffs.setStaffTel(cell.getStringCellValue());
					} else if (c == 4) {
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							staffs.setHiredate(cell.getDateCellValue());
						}
					} else if (c == 5) {
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							staffs.setLeavedate(cell.getDateCellValue());
						}
					}
				}
			}
			// 添加
			staffList.add(staffs);
		}
		return staffList;
	}

	/**
	 * 读取维修人员Excel里面信息
	 *
	 * @param wb
	 * @return
	 */
	private List<Repairman> readRepairmanExcelValue(Workbook wb) {
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);

		// 得到Excel的行数
		this.totalRows = sheet.getPhysicalNumberOfRows();

		// 得到Excel的列数(前提是有行数)
		if (totalRows >= 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<Repairman> repairmanList = new ArrayList<Repairman>();
		// 循环Excel行数,从第二行开始。标题不入库
		for (int r = 1; r < totalRows; r++) {
			Repairman repairmans = new Repairman();
			Row row = sheet.getRow(r).getCell(1).getRow();
			if (row == null)
				continue;
			// 循环Excel的列,获取相关信息
			for (int c = 0; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {
					if (c == 0) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						repairmans.setRepairmanId(Integer.parseInt(cell.getStringCellValue()));
					} else if (c == 1) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						repairmans.setRepairmanName(cell.getStringCellValue());
					} else if (c == 2) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						repairmans.setRepairmanSex(Integer.parseInt(cell.getStringCellValue()));
					} else if (c == 3) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						repairmans.setRepairmanTel(cell.getStringCellValue());
					} else if (c == 4) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						repairmans.setRepairType(Integer.parseInt(cell.getStringCellValue()));
					}
				}
			}
			// 添加
			repairmanList.add(repairmans);
		}
		return repairmanList;
	}

}
