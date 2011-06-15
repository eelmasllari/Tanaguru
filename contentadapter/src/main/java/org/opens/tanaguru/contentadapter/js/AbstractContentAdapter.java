package org.opens.tanaguru.contentadapter.js;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.opens.tanaguru.contentadapter.ContentAdapter;
import org.opens.tanaguru.contentadapter.Resource;
import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.contentloader.Downloader;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.service.audit.ContentDataService;

/**
 * 
 * @author ADEX
 */
public abstract class AbstractContentAdapter implements ContentAdapter {

    private Downloader downloader;
    public Downloader getDownloader() {
        return downloader;
    }
    
    public void setDownloader(Downloader downloader) {
        this.downloader = downloader;
    }

    protected Resource resource;
    
    private SSP ssp;
    @Override
    public SSP getSSP() {
        return ssp;
    }

    @Override
    public void setSSP(SSP ssp) {
        this.ssp = ssp;
        contentList = new ArrayList<Content>();
        try {
            urlIdentifier.setUrl(new URL(ssp.getURI()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(AbstractContentAdapter.class.getName()).warn(ex);
        }
    }

    private URLIdentifier urlIdentifier;
    public URLIdentifier getUrlIdentifier() {
        return urlIdentifier;
    }
    
    @Override
    public void setURLIdentifier(URLIdentifier urlIdentifier) {
        this.urlIdentifier = urlIdentifier;
    }
    
    private ContentFactory contentFactory;
    public ContentFactory getContentFactory() {
        return contentFactory;
    }

    @Override
    public void setContentFactory(ContentFactory contentFactory) {
        this.contentFactory = contentFactory;
    }
    
    private ContentDataService contentDataService;
    public ContentDataService getContentDataService() {
        return contentDataService;
    }

    private List<Content> contentList = new ArrayList<Content>();
    @Override
    public List<Content> getContentList() {
        return contentList;
    }

    /**
     * 
     * @param contentFactory
     * @param urlIdentifier
     * @param downloader
     * @param contentDataService
     */
    public AbstractContentAdapter(
            ContentFactory contentFactory,
            URLIdentifier urlIdentifier,
            Downloader downloader,
            ContentDataService contentDataService) {
        super();
        this.contentFactory = contentFactory;
        this.urlIdentifier = urlIdentifier;
        this.downloader = downloader;
        this.contentDataService = contentDataService;
    }

}