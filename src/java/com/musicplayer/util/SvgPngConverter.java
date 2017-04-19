package com.musicplayer.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.xml.sax.SAXException;

public class SvgPngConverter {
	/**
	 * 将svg转换为png格式的图片
	 */

	// 颜色正则表达式
	private static final String COLOR_REGEX = "#([0-9a-fA-F]{3}|[0-9a-fA-F]{6})";
	// 包含dtd的svg
	// private static final String SVG_TMP = "<?xml version=\"1.0\"
	// encoding=\"utf-8\"?><!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\"
	// \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\"><svg version=\"1.1\"
	// id=\"acgist\" xmlns=\"http://www.w3.org/2000/svg\"
	// xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0\" y=\"0\" width=\"0\"
	// height=\"0\" viewBox=\"0 0 1024 1024\"><path fill=\"#000000\" d=\"\"
	// transform=\"translate(0, 960) scale(1, -1)\" /></svg>";
	// 不含dtd的svg
	private static final String SVG_TMP = "<?xml version=\"1.0\" encoding=\"utf-8\"?><svg version=\"1.1\" id=\"acgist\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0\" y=\"0\" width=\"0\" height=\"0\" viewBox=\"0 0 1024 1024\"><path fill=\"#000000\" d=\"\" transform=\"translate(0, 960) scale(1, -1)\" /></svg>";

	/**
	 * 将svg字符串转换为png
	 * 
	 * @param svg
	 *            svg代码
	 * @param outputPath
	 *            保存的路径
	 */
	public static void convertToPng(String svg, String outputPath) {
		if (StringUtils.isEmpty(svg) || StringUtils.isEmpty(outputPath))
			return;
		File file = new File(outputPath);
		OutputStream outputStream = null;
		try {
			file.createNewFile();
			outputStream = new FileOutputStream(file);
			convertToPng(svg, outputStream);
		} catch (IOException | TranscoderException e) {
			e.printStackTrace();
		} finally {
			IOUtils.close(outputStream);
		}
	}

	/**
	 * 将svg转换成png文件，直接输出到流中
	 * 
	 * @param svg
	 *            svg代码
	 * @param outputStream
	 *            输出流
	 * @throws TranscoderException
	 *             异常
	 * @throws IOException
	 *             io异常
	 */
	public static void convertToPng(String svg, OutputStream outputStream) throws TranscoderException, IOException {
		if (StringUtils.isEmpty(svg) || outputStream == null)
			return;
		byte[] bytes = svg.getBytes("utf-8");
		PNGTranscoder pngTranscoder = new PNGTranscoder();
		TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(bytes));
		TranscoderOutput output = new TranscoderOutput(outputStream);
		pngTranscoder.transcode(input, output);
		outputStream.flush();
	}

	/**
	 * 生成svg
	 * 
	 * @param svg
	 *            svg模板
	 * @param data
	 *            数据
	 * @return svg
	 */
	public static String createSvg(String svg, String data) {
		return createSvg(svg, data, null, null, null);
	}

	/**
	 * 生成svg
	 * 
	 * @param svg
	 *            svg模板
	 * @param data
	 *            数据
	 * @param width
	 *            生成宽度，默认：100
	 * @param height
	 *            生成高度，默认：100
	 * @param color
	 *            颜色，默认：#000000
	 * @return svg
	 */
	public static String createSvg(String svg, String data, Integer width, Integer height, String color) {
		// SAXReader saxReader = new SAXReader();
		// ==================去掉dtd校验=======================//
		// saxReader.setValidation(false);
		// saxReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",
		// false);
		// ==================去掉dtd校验=======================//
		// Document document = saxReader.read(new InputSource(new
		// ByteArrayInputStream(svg.getBytes())));
		if (StringUtils.isEmpty(svg) || StringUtils.isEmpty(data))
			return null;
		if (width == null)
			width = 100;
		if (height == null)
			height = 100;
		if (StringUtils.isEmpty(color) || !color.matches(COLOR_REGEX))
			color = "#000000";
		Document document = null;
		try {
			document = DocumentHelper.parseText(svg);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		if (document == null)
			return null;
		Element root = document.getRootElement();
		Attribute widthAttr = root.attribute("width");
		if (widthAttr == null)
			root.addAttribute("width", width.toString());
		else
			widthAttr.setText(width.toString());
		Attribute heightAttr = root.attribute("height");
		if (heightAttr == null)
			root.addAttribute("height", height.toString());
		else
			heightAttr.setText(height.toString());
		Element path = root.element("path");
		Attribute colorAttr = path.attribute("fill");
		if (colorAttr == null)
			path.addAttribute("fill", color);
		else
			colorAttr.setText(color);
		Attribute dAttr = path.attribute("d");
		if (dAttr == null)
			path.addAttribute("d", data);
		else
			dAttr.setText(data);
		return root.asXML();
	}

	public static void main(String[] args) throws IOException, TranscoderException, DocumentException, SAXException {
		String svg = createSvg(SVG_TMP,
				"M829.248 539.424c-13.984 146.016-135.552 260.576-285.248 260.576-115.808 0-214.944-68.736-260.672-167.36-13.76 4.352-28.096 7.36-43.296 7.36-79.52 0-144-64.512-144-144 0-15.808 3.168-30.752 7.872-44.928-61.856-36.064-103.872-102.24-103.872-179.008 0-114.88 93.12-208 208-208v-0.064l575.968 0.064c132.576 0 240 107.424 240 240 0 116.992-83.872 214.176-194.752 235.36zM784 128.064v-0.064h-575.968c-79.392 0.064-144 64.64-144 144.064 0 51.2 26.976 97.44 72.128 123.744 43.872 25.184 46.88 30.176 28.48 75.424-3.104 9.312-4.608 17.408-4.608 24.736 0 44.128 35.872 80 80 80 0 0 20.992 1.504 43.296-7.36 36.704-14.624 40.704-0.64 58.048 37.088 36.704 79.136 116.224 130.304 202.624 130.304 115.2 0 210.432-87.136 221.568-202.688 3.936-45.824 3.936-45.824 51.68-56.736 82.752-15.808 142.752-88.384 142.752-172.512 0-97.056-78.944-176-176-176z",
				200, 200, "#CC0000");
		convertToPng(svg, "e://svg.png");
	}

}
