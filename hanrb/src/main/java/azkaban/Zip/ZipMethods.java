package azkaban.Zip;

import azkaban.Base.PathParam;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ZipMethods {
    public  String jarPath;
    public  String zipPath;
    public  String zipName;

    public ZipMethods(String projectName){
        zipPath= PathParam.zipPath;
        zipName= PathParam.zipName;
        JarMethods jar=new JarMethods(projectName);
        if(jar.getJarNameAndPath()&&jar.renameJar()){
            jarPath=jar.getJarPath()+jar.getJarName();
            System.out.println("zip包资源准备成功");
        }
        else{
            System.out.println("zip包资源准备失败");
        }
    }
    /**
     * 将yaml文件和jar包压缩成zip包
     */
    public void creatZipPackage(){
        if(jarPath!=null){
            moveFile(jarPath);
            //fileToZip(zipPath,zipPath,zipName);
        }
    }

    /**
     * 将jar包复制到指定目录下
     * @param filePath jar包文件路径
     */
    public  void moveFile(String filePath){
        //获取源文件的名称
        String newFileName = filePath.substring(filePath.lastIndexOf("\\")+1); //目标文件地址
        System.out.println("源文件:"+newFileName);
        zipPath = zipPath + File.separator + newFileName; //源文件地址
        System.out.println("目标文件地址:"+zipPath);
        try
        {
            FileInputStream fis = new FileInputStream(filePath);//创建输入流对象
            FileOutputStream fos = new FileOutputStream(zipPath); //创建输出流对象
            byte datas[] = new byte[1024*8];//创建搬运工具
            int len = 0;//创建长度
            while((len = fis.read(datas))!=-1)//循环读取数据
            {
                fos.write(datas,0,len);
            }
            fis.close();//释放资源
            fis.close();//释放资源
            System.out.println("文件迁移成功...");
        }
        catch (Exception e)
        {
            System.out.println("文件迁移失败...");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param sourceFilePath 待压缩文件目录
     * @param zipFilePath 指定压缩包生成目录
     * @param fileName 指定压缩包名称
     * @return 是否压缩成功
     */
    public  boolean fileToZip(String sourceFilePath,String zipFilePath,String fileName){
        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        if(sourceFile.exists() == false){
            System.out.println("待压缩的文件目录："+sourceFilePath+"不存在.");
        }else{
            try {
                File zipFile = new File(zipFilePath + "/" + fileName +".zip");
                if(zipFile.exists()){
                    System.out.println(zipFilePath + "目录下存在名字为:" + fileName +".zip" +"打包文件.");
                }else{
                    File[] sourceFiles = sourceFile.listFiles();
                    if(null == sourceFiles || sourceFiles.length<1){
                        System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
                    }else{
                        fos = new FileOutputStream(zipFile);
                        zos = new ZipOutputStream(new BufferedOutputStream(fos));
                        byte[] bufs = new byte[1024*10];
                        for(int i=0;i<sourceFiles.length;i++){
                            //创建ZIP实体，并添加进压缩包
                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                            zos.putNextEntry(zipEntry);
                            //读取待压缩的文件并写进压缩包里
                            fis = new FileInputStream(sourceFiles[i]);
                            bis = new BufferedInputStream(fis, 1024*10);
                            int read = 0;
                            while((read=bis.read(bufs, 0, 1024*10)) != -1){
                                zos.write(bufs,0,read);
                            }
                        }
                        flag = true;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally{
                //关闭流
                try {
                    if(null != bis) bis.close();
                    if(null != zos) zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
        return flag;
    }
}
