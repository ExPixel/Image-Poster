package org.imageposter.imgur;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.imageposter.PosterUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ImgurImage {
	String name, title, caption, hash,
	deletehash, datetime, type, animated, width, height, size,
	views, bandwidth;
	private String xmlText;
	ImgurImageLinks links;

	public static ImgurImage fromXML(final String xml) throws SAXException, IOException, ParserConfigurationException {

		final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		final Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));
		doc.getDocumentElement().normalize();

		final Element upload = (Element) doc.getFirstChild();

		final ImgurImage _imgurimg = new ImgurImage(PosterUtil.XMLFirstElem(upload, "image"));
		_imgurimg.setLinks(new ImgurImageLinks(PosterUtil.XMLFirstElem(upload, "links")));

		return _imgurimg;
	}

	public ImgurImage() {}

	private ImgurImage(final Element imageElement) {
		this.setName(PosterUtil.XMLFirstElem(imageElement, "name").getTextContent());
		this.setTitle(PosterUtil.XMLFirstElem(imageElement, "title").getTextContent());
		this.setCaption(PosterUtil.XMLFirstElem(imageElement, "caption").getTextContent());
		this.setHash(PosterUtil.XMLFirstElem(imageElement, "hash").getTextContent());
		this.setDeletehash(PosterUtil.XMLFirstElem(imageElement, "deletehash").getTextContent());
		this.setType(PosterUtil.XMLFirstElem(imageElement, "type").getTextContent());
		this.setAnimated(PosterUtil.XMLFirstElem(imageElement, "animated").getTextContent());
		this.setWidth(PosterUtil.XMLFirstElem(imageElement, "width").getTextContent());
		this.setHeight(PosterUtil.XMLFirstElem(imageElement, "height").getTextContent());
		this.setSize(PosterUtil.XMLFirstElem(imageElement, "size").getTextContent());
		this.setViews(PosterUtil.XMLFirstElem(imageElement, "views").getTextContent());
		this.setBandwidth(PosterUtil.XMLFirstElem(imageElement, "bandwidth").getTextContent());
	}
	/*
	<upload>
	  <image>
	    <name>Me at the zoo</name>
	    <title></title>
	    <caption></caption>
	    <hash>WWs55</hash>
	    <deletehash>Dte3mMaQzGoc8RF</deletehash>
	    <datetime>2010-08-16 22:39:19</datetime>
	    <type>image/jpeg</type>
	    <animated>false</animated>
	    <width>720</width>
	    <height>540</height>
	    <size>46174</size>
	    <views>0</views>
	    <bandwidth>0</bandwidth>
	  </image>
	  <links>
	    <original>http://imgur.com/WWs55.jpg</original>
	    <imgur_page>http://imgur.com/WWs55</imgur_page>
	    <delete_page>http://imgur.com/delete/Dte3mMaQzGoc8RF</delete_page>
	    <small_square>http://imgur.com/WWs55s.jpg</small_square>
	    <large_thumbnail>http://imgur.com/WWs55l.jpg</large_thumbnail>
	  </links>
	</upload>
	 */

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @return the xmlText
	 */
	public String getXmlText() {
		return this.xmlText;
	}

	/**
	 * @param xmlText the xmlText to set
	 */
	public void setXmlText(final String xmlText) {
		this.xmlText = xmlText;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * @return the caption
	 */
	public String getCaption() {
		return this.caption;
	}

	/**
	 * @param caption the caption to set
	 */
	public void setCaption(final String caption) {
		this.caption = caption;
	}

	/**
	 * @return the hash
	 */
	public String getHash() {
		return this.hash;
	}

	/**
	 * @param hash the hash to set
	 */
	public void setHash(final String hash) {
		this.hash = hash;
	}

	/**
	 * @return the deletehash
	 */
	public String getDeletehash() {
		return this.deletehash;
	}

	/**
	 * @param deletehash the deletehash to set
	 */
	public void setDeletehash(final String deletehash) {
		this.deletehash = deletehash;
	}

	/**
	 * @return the datetime
	 */
	public String getDatetime() {
		return this.datetime;
	}

	/**
	 * @param datetime the datetime to set
	 */
	public void setDatetime(final String datetime) {
		this.datetime = datetime;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * @return the animated
	 */
	public String getAnimated() {
		return this.animated;
	}

	/**
	 * @param animated the animated to set
	 */
	public void setAnimated(final String animated) {
		this.animated = animated;
	}

	/**
	 * @return the width
	 */
	public String getWidth() {
		return this.width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(final String width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return this.height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(final String height) {
		this.height = height;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return this.size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(final String size) {
		this.size = size;
	}

	/**
	 * @return the views
	 */
	public String getViews() {
		return this.views;
	}

	/**
	 * @param views the views to set
	 */
	public void setViews(final String views) {
		this.views = views;
	}

	/**
	 * @return the bandwidth
	 */
	public String getBandwidth() {
		return this.bandwidth;
	}

	/**
	 * @param bandwidth the bandwidth to set
	 */
	public void setBandwidth(final String bandwidth) {
		this.bandwidth = bandwidth;
	}

	/**
	 * @return the links
	 */
	public ImgurImageLinks getLinks() {
		return this.links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(final ImgurImageLinks links) {
		this.links = links;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ImgurImage [name=" + this.name + ", title=" + this.title + ", caption="
				+ this.caption + ", hash=" + this.hash + ", deletehash=" + this.deletehash
				+ ", datetime=" + this.datetime + ", type=" + this.type + ", animated="
				+ this.animated + ", width=" + this.width + ", height=" + this.height
				+ ", size=" + this.size + ", views=" + this.views + ", bandwidth="
				+ this.bandwidth + ", xmlText=" + this.xmlText + ", links=" + this.links.toString() + "]";
	}



}


