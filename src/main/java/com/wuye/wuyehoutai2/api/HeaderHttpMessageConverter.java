package com.wuye.wuyehoutai2.api;


/**
 * Created by yaohaibo on 16/9/19.
 */
public class HeaderHttpMessageConverter {
    //public class HeaderHttpMessageConverter extends FastJsonHttpMessageConverter {

    //	private static final Logger log = LoggerFactory.getLogger(HeaderHttpMessageConverter.class);
    //
    //	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    //
    //	private static final String PARAM_CHARSET = "charset";
    //
    //	private static final String FORM_TYPE = "x-www-form-urlencoded";
    //
    //	private static final ThreadLocal<Long> CURR_USER = new ThreadLocal<>();
    //	private static final MediaType META_TYPE = new MediaType("application", "json", DEFAULT_CHARSET);
    //	private static Map<Type, HeaderCheck> CHECK_CACHE = new ConcurrentHashMap<>();
    //
    //	@Setter
    //	private AccessTokenCheck accessTokenCheck;
    //	@Setter
    //	private ResponseExtension responseExtension;
    //
    //	@Override
    //	public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
    //			throws IOException, HttpMessageNotReadableException {
    //
    //		Object obj = readParam(type, inputMessage);
    //
    //		HttpHeaders headers = inputMessage.getHeaders();
    //		HttpRequest header = null;
    //		HttpEntryRequest newReq = null;
    //		if (obj instanceof HttpEntryRequest) {
    //			newReq = (HttpEntryRequest) obj;
    //			obj = newReq.getData();
    //			Map<String, String> authHeader = newReq.getAuthHeader();
    //			for (Map.Entry<String, String> map : authHeader.entrySet()) {
    //				headers.add(map.getKey(), map.getValue());
    //			}
    //		}
    //
    //		log.info("request header:{},request body:{}",
    //				new Object[] { JSON.toJSONString(headers), JSON.toJSONString(obj) });
    //
    //		if (obj instanceof HttpRequest) {
    //			header = (HttpRequest) obj;
    //		} else {
    //			header = new HttpRequest("") {
    //			};
    //		}
    //
    //		HeaderCheck headerCheck = null;
    //		Error err = null;
    //
    //		if (header != null) {
    //			if ((headerCheck = CHECK_CACHE.get(type)) == null) {
    //				headerCheck = (HeaderCheck) ((Class) type).getAnnotation(HeaderCheck.class);
    //				if (headerCheck == null) {
    //					headerCheck = newHeaderCheck();
    //				}
    //
    //				CHECK_CACHE.putIfAbsent(type, headerCheck);
    //			}
    //			err = checkHeader(headers, header, headerCheck);
    //		}
    //
    //		CURR_USER.set(header.getUserId());
    //
    //		if (err != null) {
    //			throw new HeaderCheckFailedException(err);
    //		}
    //
    //		return obj;
    //	}
    //
    //	@Override
    //	protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException {
    //		ServiceResponse rs = null;
    //		if (o instanceof ServiceResponse) {
    //			rs = (ServiceResponse) o;
    //		} else if (o instanceof LinkedHashMap) {
    //			Integer status = (Integer) (((LinkedHashMap) o).get("status"));
    //			if (status != null && status >= 200 && status <= 299) {
    //				rs = new ServiceResponse(CommonError.SUCCESS);
    //			} else {
    //				rs = new ServiceResponse(CommonError.UNKNOW_ERROR.getCode(),
    //						(String) (((LinkedHashMap) o).get("message")));
    //			}
    //		} else {
    //			rs = new ServiceResponse(CommonError.SUCCESS, o);
    //		}
    //
    //		// 用户已登录,且请求被正常处理的情况下,返回用户的扩展信息
    //		if (responseExtension != null && rs.isSuccess() && CURR_USER.get() != null) {
    //			rs.setExtension(responseExtension.getExtensions(CURR_USER.get()));
    //		}
    //
    //		String stringResult = JSON.toJSON(rs).toString();
    //		byte[] payload = stringResult.getBytes();
    //
    //		outputMessage.getHeaders().setContentType(META_TYPE);
    //		outputMessage.getHeaders().setContentLength(payload.length);
    //		outputMessage.getBody().write(payload);
    //		outputMessage.getBody().flush();
    //	}
    //
    //	private Error checkHeader(HttpHeaders headers, HttpRequest param, HeaderCheck checkInfo) {
    //
    //		String accessToken = headers.getFirst(HeaderKey.ACCESS_TOKEN);
    //		String clientToken = headers.getFirst(HeaderKey.CLIENT_TOKEN);
    //		Long userId = parseUserId(headers.getFirst(HeaderKey.USER_ID));
    //
    //		if (checkInfo.clientToken()) {
    //			if (StringUtils.isEmpty(headers.getFirst(HeaderKey.CLIENT_TOKEN))) {
    //				return WebCommonError.NEED_CLIENT_TOKEN;
    //			}
    //			param.setClientToken(headers.getFirst(HeaderKey.CLIENT_TOKEN));
    //		}
    //		if (checkInfo.clientVersion()) {
    //			if (StringUtils.isEmpty(headers.getFirst(HeaderKey.CLIENT_VERSION))) {
    //				return WebCommonError.NEED_CLIENT_VERSION;
    //			}
    //			param.setClientVersion(headers.getFirst(HeaderKey.CLIENT_VERSION));
    //		}
    //		if (checkInfo.methodVersion()) {
    //			if (StringUtils.isEmpty(headers.getFirst(HeaderKey.METHOD_VERSION))) {
    //				return WebCommonError.NEED_METHOD_VERSION;
    //			}
    //			param.setMethodVersion(headers.getFirst(HeaderKey.METHOD_VERSION));
    //		}
    //		if (checkInfo.networkType()) {
    //			if (StringUtils.isEmpty(headers.getFirst(HeaderKey.NETWORK_TYPE))) {
    //				return WebCommonError.NEED_NETWORK_TYPE;
    //			}
    //			param.setNetworkType(headers.getFirst(HeaderKey.NETWORK_TYPE));
    //		}
    //		if (checkInfo.platform()) {
    //			if (StringUtils.isEmpty(headers.getFirst(HeaderKey.PLATFORM))
    //					&& StringUtils.isEmpty(headers.getFirst(HeaderKey.PLANTFORM))) {
    //				return WebCommonError.NEED_PLATFORM;
    //			}
    //			param.setPlatform(headers.getFirst(HeaderKey.PLATFORM));
    //		}
    //		if (checkInfo.realIp()) {
    //			if (StringUtils.isEmpty(headers.getFirst(HeaderKey.REAL_IP))) {
    //				return WebCommonError.NEED_REAL_IP;
    //			}
    //			param.setRealIp(headers.getFirst(HeaderKey.REAL_IP));
    //		}
    //		if (checkInfo.systemVersion()) {
    //			if (StringUtils.isEmpty(headers.getFirst(HeaderKey.SYSTEM_VERSION))) {
    //				return WebCommonError.NEED_SYSTEM_VERSION;
    //			}
    //			param.setSystemVersion(headers.getFirst(HeaderKey.SYSTEM_VERSION));
    //		}
    //		// 当客户端有传入用户信息,或者当前接口需要校验用户身份时
    //		if ((accessToken != null && clientToken != null && userId != null) || checkInfo.accessToken()) {
    //			if (StringUtils.isEmpty(headers.getFirst(HeaderKey.ACCESS_TOKEN))) {
    //				return WebCommonError.NEED_ACCESS_TOKEN;
    //			}
    //
    //			// 兼容老版本userId作为参数在request body 中的情况
    //			userId = userId == null ? param.getUserId() : userId;
    //
    //			param.setAccessToken(accessToken);
    //			param.setUserId(userId);
    //			param.setClientToken(clientToken);
    //
    //			if (accessTokenCheck != null && !accessTokenCheck.checkAccessRights(param)) {
    //				return WebCommonError.ACCESS_TOKEN_CHECK_FAILD;
    //			}
    //
    //		}
    //
    //		return null;
    //	}
    //
    //	private Long parseUserId(String userId) {
    //		if (StringUtils.isEmpty(userId)) {
    //			return null;
    //		}
    //
    //		try {
    //			Long uid = Long.valueOf(userId);
    //			return uid;
    //		} catch (Exception e) {
    //			return null;
    //		}
    //	}
    //
    //	private HeaderCheck newHeaderCheck() {
    //		return new HeaderCheck() {
    //			@Override
    //			public Class<? extends Annotation> annotationType() {
    //				return null;
    //			}
    //
    //			@Override
    //			public boolean accessToken() {
    //				return false;
    //			}
    //
    //			@Override
    //			public boolean realIp() {
    //				return false;
    //			}
    //
    //			@Override
    //			public boolean platform() {
    //				return true;
    //			}
    //
    //			@Override
    //			public boolean systemVersion() {
    //				return true;
    //			}
    //
    //			@Override
    //			public boolean clientVersion() {
    //				return true;
    //			}
    //
    //			@Override
    //			public boolean clientToken() {
    //				return true;
    //			}
    //
    //			@Override
    //			public boolean methodVersion() {
    //				return true;
    //			}
    //
    //			@Override
    //			public boolean networkType() {
    //				return true;
    //			}
    //		};
    //	}
    //
    //	private Object readParam(Type type, HttpInputMessage httpInputMessage)
    //			throws IOException, HttpMessageNotReadableException {
    //		Class clazz = (Class) type;
    //		String subtype = httpInputMessage.getHeaders().getContentType().getSubtype();
    //		if (!FORM_TYPE.equalsIgnoreCase(subtype)) {
    //			return super.readInternal(clazz, httpInputMessage);
    //		}
    //		Charset charset = getContentTypeCharset(httpInputMessage.getHeaders().getContentType());
    //		String orgText = StreamUtils.copyToString(httpInputMessage.getBody(), charset);
    //		String[] datas = orgText.split("&");
    //		Object data = null;
    //		Map<String, String> auth = null;
    //		for (String dataText : datas) {
    //			String[] segs = dataText.split("=");
    //			String valText = URLDecoder.decode(segs[1], charset.displayName());
    //			if ("data".equals(segs[0])) {
    //				data = JSON.parseObject(valText, clazz);
    //			} else if ("auth".equals(segs[0])) {
    //				auth = (Map<String, String>) JSON.parseObject(valText, Map.class);
    //			} else {
    //			}
    //		}
    //		if (data == null || auth == null) {
    //			throw new NullPointerException("data or auth must not be null");
    //		}
    //		return new HttpEntryRequest(data, auth);
    //	}
    //
    //	private Charset getContentTypeCharset(MediaType contentType) {
    //		if (contentType != null && getCharset(contentType) != null) {
    //			return getCharset(contentType);
    //		} else {
    //			return DEFAULT_CHARSET;
    //		}
    //	}
    //
    //	/**
    //	 * 由于本项目依赖spring core 4.2.7 而spring core 4.3.0以上开始contentType.getCharSet()
    //	 * 方法改为contentType.getCharset()<br />
    //	 * 因此当应用依赖spring core 4.3.0 以上版本时。运行时会抛NoSuchMethod <br />
    //	 * 为防止应用在运行时才触发此问题。因此没有采用排JAR包和强制升级的方式。<br />
    //	 * 在此类复写getCharset方法。用来同时支持应用依赖spring core 4.3.0以下或以上的情况。应用无需感知<br />
    //	 *
    //	 *
    //	 * @param contentType
    //	 * @return
    //	 */
    //	private Charset getCharset(MediaType contentType) {
    //		String charSet = contentType.getParameter(PARAM_CHARSET);
    //		return (charSet != null ? Charset.forName(unquote(charSet)) : null);
    //	}
    //
    //	private boolean isQuotedString(String s) {
    //		if (s.length() < 2) {
    //			return false;
    //		} else {
    //			return ((s.startsWith("\"") && s.endsWith("\"")) || (s.startsWith("'") && s.endsWith("'")));
    //		}
    //	}
    //
    //	protected String unquote(String s) {
    //		if (s == null) {
    //			return null;
    //		}
    //		return isQuotedString(s) ? s.substring(1, s.length() - 1) : s;
    //	}
}
