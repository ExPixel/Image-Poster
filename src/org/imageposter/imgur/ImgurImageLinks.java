package org.imageposter.imgur;

import org.imageposter.PosterUtil;
import org.w3c.dom.Element;

public class ImgurImageLinks {
	String original, imgur_page, delete_page, small_square, large_thumbnail;

	public ImgurImageLinks() {}
	public ImgurImageLinks(final Element links) {
		this.setOriginal(PosterUtil.XMLFirstElem(links, "original").getTextContent());
		this.setImgur_page(PosterUtil.XMLFirstElem(links, "imgur_page").getTextContent());
		this.setDelete_page(PosterUtil.XMLFirstElem(links, "delete_page").getTextContent());
		this.setSmall_square(PosterUtil.XMLFirstElem(links, "small_square").getTextContent());
		this.setLarge_thumbnail(PosterUtil.XMLFirstElem(links, "large_thumbnail").getTextContent());
	}


	/**
	 * @return the original
	 */
	public String getOriginal() {
		return this.original;
	}

	/**
	 * @param original the original to set
	 */
	public void setOriginal(final String original) {
		this.original = original;
	}

	/**
	 * @return the imgur_page
	 */
	public String getImgur_page() {
		return this.imgur_page;
	}
	/**
	 * @param imgur_page the imgur_page to set
	 */
	public void setImgur_page(final String imgur_page) {
		this.imgur_page = imgur_page;
	}
	/**
	 * @return the delete_page
	 */
	public String getDelete_page() {
		return this.delete_page;
	}

	/**
	 * @param delete_page the delete_page to set
	 */
	public void setDelete_page(final String delete_page) {
		this.delete_page = delete_page;
	}

	/**
	 * @return the small_square
	 */
	public String getSmall_square() {
		return this.small_square;
	}

	/**
	 * @param small_square the small_square to set
	 */
	public void setSmall_square(final String small_square) {
		this.small_square = small_square;
	}

	/**
	 * @return the large_thumbnail
	 */
	public String getLarge_thumbnail() {
		return this.large_thumbnail;
	}

	/**
	 * @param large_thumbnail the large_thumbnail to set
	 */
	public void setLarge_thumbnail(final String large_thumbnail) {
		this.large_thumbnail = large_thumbnail;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ImagurImageLinks [original=" + this.original + ", delete_page="
				+ this.delete_page + ", small_square=" + this.small_square
				+ ", large_thumbnail=" + this.large_thumbnail + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.delete_page == null) ? 0 : this.delete_page.hashCode());
		result = prime * result
				+ ((this.large_thumbnail == null) ? 0 : this.large_thumbnail.hashCode());
		result = prime * result
				+ ((this.original == null) ? 0 : this.original.hashCode());
		result = prime * result
				+ ((this.small_square == null) ? 0 : this.small_square.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final ImgurImageLinks other = (ImgurImageLinks) obj;
		if (this.delete_page == null) {
			if (other.delete_page != null) {
				return false;
			}
		} else if (!this.delete_page.equals(other.delete_page)) {
			return false;
		}
		if (this.large_thumbnail == null) {
			if (other.large_thumbnail != null) {
				return false;
			}
		} else if (!this.large_thumbnail.equals(other.large_thumbnail)) {
			return false;
		}
		if (this.original == null) {
			if (other.original != null) {
				return false;
			}
		} else if (!this.original.equals(other.original)) {
			return false;
		}
		if (this.small_square == null) {
			if (other.small_square != null) {
				return false;
			}
		} else if (!this.small_square.equals(other.small_square)) {
			return false;
		}
		return true;
	}

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
