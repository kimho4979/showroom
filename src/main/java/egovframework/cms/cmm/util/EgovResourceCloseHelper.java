/*     */ package egovframework.cms.cmm.util;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import java.sql.Connection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.Statement;
/*     */ import java.sql.Wrapper;
/*     */ 
/*     */ public class EgovResourceCloseHelper
/*     */ {
/*     */   public static void close(Closeable[] resources)
/*     */   {
/*  33 */     Closeable[] arrayOfCloseable = resources; int j = resources.length; for (int i = 0; i < j; i++) { Closeable resource = arrayOfCloseable[i];
/*  34 */       if (resource != null)
/*     */         try {
/*  36 */           resource.close();
/*     */         } catch (Exception ignore) {
/*  38 */           EgovBasicLogger.ignore("Occurred Exception to close resource is ingored!!");
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void closeDBObjects(Wrapper[] objects)
/*     */   {
/*  49 */     Wrapper[] arrayOfWrapper = objects; int j = objects.length; for (int i = 0; i < j; i++) { Object object = arrayOfWrapper[i];
/*  50 */       if (object != null)
/*  51 */         if ((object instanceof ResultSet))
/*     */           try {
/*  53 */             ((ResultSet)object).close();
/*     */           } catch (Exception ignore) {
/*  55 */             EgovBasicLogger.ignore("Occurred Exception to close resource is ingored!!");
/*     */           }
/*  57 */         else if ((object instanceof Statement))
/*     */           try {
/*  59 */             ((Statement)object).close();
/*     */           } catch (Exception ignore) {
/*  61 */             EgovBasicLogger.ignore("Occurred Exception to close resource is ingored!!");
/*     */           }
/*  63 */         else if ((object instanceof Connection))
/*     */           try {
/*  65 */             ((Connection)object).close();
/*     */           } catch (Exception ignore) {
/*  67 */             EgovBasicLogger.ignore("Occurred Exception to close resource is ingored!!");
/*     */           }
/*     */         else
/*  70 */           throw new IllegalArgumentException("Wrapper type is not found : " + object.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void closeSocketObjects(Socket socket, ServerSocket server)
/*     */   {
/*  81 */     if (socket != null) {
/*     */       try {
/*  83 */         socket.shutdownOutput();
/*     */       } catch (Exception ignore) {
/*  85 */         EgovBasicLogger.ignore("Occurred Exception to shutdown ouput is ignored!!");
/*     */       }
/*     */       try
/*     */       {
/*  89 */         socket.close();
/*     */       } catch (Exception ignore) {
/*  91 */         EgovBasicLogger.ignore("Occurred Exception to close resource is ignored!!");
/*     */       }
/*     */     }
/*     */ 
/*  95 */     if (server != null)
/*     */       try {
/*  97 */         server.close();
/*     */       } catch (Exception ignore) {
/*  99 */         EgovBasicLogger.ignore("Occurred Exception to close resource is ignored!!");
/*     */       }
/*     */   }
/*     */ 
/*     */   public static void closeSockets(Socket[] sockets)
/*     */   {
/* 110 */     Socket[] arrayOfSocket = sockets; int j = sockets.length; for (int i = 0; i < j; i++) { Socket socket = arrayOfSocket[i];
/* 111 */       if (socket != null) {
/*     */         try {
/* 113 */           socket.shutdownOutput();
/*     */         } catch (Exception ignore) {
/* 115 */           EgovBasicLogger.ignore("Occurred Exception to shutdown ouput is ignored!!");
/*     */         }
/*     */         try
/*     */         {
/* 119 */           socket.close();
/*     */         } catch (Exception ignore) {
/* 121 */           EgovBasicLogger.ignore("Occurred Exception to close resource is ignored!!");
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.util.EgovResourceCloseHelper
 * JD-Core Version:    0.6.2
 */