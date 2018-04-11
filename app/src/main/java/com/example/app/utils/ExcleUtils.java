package com.example.app.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.widget.Toast;

import com.example.app.MyApp;
import com.example.app.cache.Cache;
import com.example.app.db.DBHelper;
import com.example.app.db.GroupDBHelper;
import com.example.app.db.JingZhiDBHelper;
import com.example.app.model.ChiCang;
import com.example.app.model.Group;
import com.example.app.model.Jingzhi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Created by hello on 2018/4/9.
 */

public class ExcleUtils {

        //内存地址
        public static String root = Environment.getExternalStorageDirectory()
                .getPath();

        public static File writeExcel(Context context) throws Exception {
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)&&getAvailableStorage()>1000000) {
                Toast.makeText(context, "SD卡不可用", Toast.LENGTH_LONG).show();
                return null;
            }
            GroupDBHelper groupDBHelper=new GroupDBHelper(DBHelper.getRealm());
            JingZhiDBHelper jingZhiDBHelper=new JingZhiDBHelper(DBHelper.getRealm());


            File file;
            File dir = new File(context.getExternalFilesDir(null).getPath());
            file = new File(dir, Cache.getDaoChuName(MyApp.context)+Cache.getupdateDate(MyApp.context) + ".xls");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // 创建Excel工作表
            WritableWorkbook wwb;
            OutputStream os = new FileOutputStream(file);
            wwb = Workbook.createWorkbook(os);

            List<Group> groups=groupDBHelper.findAllGroup();
            // 添加第一个工作表并设置第一个Sheet的名字
            String[] zuhetitle = { "组合名称", "总市值", "持仓市值", "最大回撤","日收益" };
            WritableSheet sheet1 = wwb.createSheet("组合列表", 0);
            Label labe2;
            for (int i = 0; i < zuhetitle.length; i++) {
                // Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
                // 在Label对象的子对象中指明单元格的位置和内容
                labe2 = new Label(i, 0, zuhetitle[i], getHeader());
                // 将定义好的单元格添加到工作表中
                sheet1.addCell(labe2);
            }
            for (int i = 0; i < groups.size(); i++) {
                Group group=groups.get(i);
                Label zhmc = new Label(0, i + 1, group.getName());
                Label zsz = new Label(1, i + 1, String.valueOf(group.getTotalValue()));
                Label ccsz = new Label(2,i+1,UiUtils.format2wei(group.getMarketValue()));
                Label zdhc = new Label(3, i + 1, UiUtils.format2wei(group.getMarketValue()));
                Label rsy = new Label(3, i + 1, UiUtils.format2wei(group.getProfit()));
                sheet1.addCell(zhmc);
                sheet1.addCell(zsz);
                sheet1.addCell(ccsz);
                sheet1.addCell(zdhc);
                sheet1.addCell(rsy);
            }
            String[] chicangtitle = { "基金代码", "基金名称", "持仓量", "成本价","最新收盘价","持仓市值" };
            for(int j=0;j<groups.size();j++){
                Group group=groups.get(j);
                WritableSheet sheet = wwb.createSheet(group.getName(), j+1);
                Label label;
                for (int i = 0; i < chicangtitle.length; i++) {
                    // Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
                    // 在Label对象的子对象中指明单元格的位置和内容
                    label = new Label(i, 0, chicangtitle[i], getHeader());
                    // 将定义好的单元格添加到工作表中
                    sheet.addCell(label);
                }

                for (int i = 0; i < group.getChicang().size(); i++) {
                    ChiCang chiCang=group.getChicang().get(i);
                    Label jjdm = new Label(0, i + 1, chiCang.getJingzhi().getDaima());
                    Label jjmc = new Label(1, i + 1, chiCang.getJingzhi().getName());
                    Label ccl = new Label(2,i+1,chiCang.getChicangliang().toString());
                    Label cbj = new Label(3, i + 1, chiCang.getJingzhi().getDwjz1());
                    sheet.addCell(jjdm);
                    sheet.addCell(jjmc);
                    sheet.addCell(ccl);
                    sheet.addCell(cbj);
                    Jingzhi jingzhi=jingZhiDBHelper.findJingzhiByID(chiCang.getJingzhi().getDaima());
                    if(jingzhi!=null){
                        Label zxspj = new Label(3, i + 1,jingzhi.getDwjz1());
                        Label ccsz = new Label(3, i + 1,String.valueOf(Double.parseDouble(jingzhi.getDwjz1())*chiCang.getChicangliang()));
                        sheet.addCell(zxspj);
                        sheet.addCell(ccsz);
                    }
                }
            }

            // 写入数据
            wwb.write();
            // 关闭文件
            wwb.close();
            return file;
        }
    public static File writeExcel(Context context, List<Order> exportOrder,
                                  String fileName) throws Exception {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)&&getAvailableStorage()>1000000) {
            Toast.makeText(context, "SD卡不可用", Toast.LENGTH_LONG).show();
            return null;
        }
        String[] title = { "订单", "店名", "电话", "地址" };
        File file;
        File dir = new File(context.getExternalFilesDir(null).getPath());
        file = new File(dir, fileName + ".xls");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 创建Excel工作表
        WritableWorkbook wwb;
        OutputStream os = new FileOutputStream(file);
        wwb = Workbook.createWorkbook(os);
        // 添加第一个工作表并设置第一个Sheet的名字
        WritableSheet sheet = wwb.createSheet("订单", 0);
        Label label;
        for (int i = 0; i < title.length; i++) {
            // Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
            // 在Label对象的子对象中指明单元格的位置和内容
            label = new Label(i, 0, title[i], getHeader());
            // 将定义好的单元格添加到工作表中
            sheet.addCell(label);
        }

        for (int i = 0; i < exportOrder.size(); i++) {
            Order order = exportOrder.get(i);

            Label orderNum = new Label(0, i + 1, order.id);
            Label restaurant = new Label(1, i + 1, order.restName);
            Label nameLabel = new Label(2,i+1,order.restPhone);
            Label address = new Label(3, i + 1, order.receiverAddr);

            sheet.addCell(orderNum);
            sheet.addCell(restaurant);
            sheet.addCell(nameLabel);
            sheet.addCell(address);


        }
        // 写入数据
        wwb.write();
        // 关闭文件
        wwb.close();
        return file;
    }
        public static WritableCellFormat getHeader() {
            WritableFont font = new WritableFont(WritableFont.TIMES, 10,
                    WritableFont.BOLD);// 定义字体
            try {
                font.setColour(Colour.BLUE);// 蓝色字体
            } catch (WriteException e1) {
                e1.printStackTrace();
            }
            WritableCellFormat format = new WritableCellFormat(font);
            try {
                format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
                format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
                // format.setBorder(Border.ALL, BorderLineStyle.THIN,
                // Colour.BLACK);// 黑色边框
                // format.setBackground(Colour.YELLOW);// 黄色背景
            } catch (WriteException e) {
                e.printStackTrace();
            }
            return format;
        }

        /** 获取SD可用容量 */
        private static long getAvailableStorage() {

            StatFs statFs = new StatFs(root);
            long blockSize = statFs.getBlockSize();
            long availableBlocks = statFs.getAvailableBlocks();
            long availableSize = blockSize * availableBlocks;
            // Formatter.formatFileSize(context, availableSize);
            return availableSize;
        }

}
