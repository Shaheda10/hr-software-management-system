/*
 * package com.hr.management.filter;
 * 
 * import com.fasterxml.jackson.databind.ObjectMapper;
 * 
 * public class JsonFilter {
 * 
 * private final String requestJsonString; BufferedReader bufferedReader = new
 * BufferedReader(new InputStreamReader(System.in));
 * 
 * public JsonFilterWrapper(HttpServletRequest httpServletRequest) throws
 * IOException { super(httpServletRequest); try { StringBuilder stringBuilder =
 * new StringBuilder(); bufferedReader = httpServletRequest.getReader(); char[]
 * charBuffer = new char[128]; int bytesRead; while ((bytesRead =
 * bufferedReader.read(charBuffer)) != -1) { stringBuilder.append(charBuffer, 0,
 * bytesRead); } requestJsonString = stringBuilder.toString();
 * System.out.println(requestJsonString); System.out.println(new
 * ObjectMapper().readTree(requestJsonString)); } catch (JsonProcessingException
 * e) { throw new RepresentationException(e.getMessage(), REQUEST_INVALID,
 * BAD_REQUEST); } finally { bufferedReader.close(); } }
 * 
 * public String getRequestJson() { return this.requestJsonString; }
 * 
 * @Override public ServletInputStream getInputStream() { return new
 * InputStreamWrapper(requestJsonString.getBytes()); }
 * 
 * @Override public BufferedReader getReader() { return new BufferedReader(new
 * InputStreamReader(this.getInputStream())); } }
 */