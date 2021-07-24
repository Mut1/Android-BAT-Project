### Bitmap内存占用计算

占用的内存大小=像素总数量（图片宽x高）x每个像素的字节大小

public static final Bitmap.Config ALPHA_8 　//代表8位Alpha位图 每个像素占用1byte内存

public static final Bitmap.Config ARGB_4444 　//代表16位ARGB位图 每个像素占用2byte内存

==public static final Bitmap.Config ARGB_8888 　//代表32位ARGB位图 每个像素占用4byte内存==

public static final Bitmap.Config RGB_565 　//代表8位RGB位图 每个像素占用2byte内存

假设这张图片是ARGB_8888的，那这张图片占的内存就是 width * height * 4个字节或者 width * height * inTargetDensity /inDensity * 4

