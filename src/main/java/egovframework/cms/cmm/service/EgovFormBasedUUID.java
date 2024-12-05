/*     */ package egovframework.cms.cmm.service;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.Serializable;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.SecureRandom;
/*     */ 
/*     */ public class EgovFormBasedUUID
/*     */   implements Serializable
/*     */ {
/*     */   private final long mostSigBits;
/*     */   private final long leastSigBits;
/*  79 */   private transient int version = -1;
/*     */ 
/*  84 */   private transient int variant = -1;
/*     */ 
/*  89 */   private volatile transient long timestamp = -1L;
/*     */ 
/*  94 */   private transient int sequence = -1;
/*     */ 
/*  99 */   private transient long node = -1L;
/*     */ 
/* 104 */   private transient int hashCode = -1;
/*     */ 
/* 110 */   private static volatile SecureRandom numberGenerator = null;
/*     */ 
/*     */   private EgovFormBasedUUID(byte[] data)
/*     */   {
/* 118 */     long msb = 0L;
/* 119 */     long lsb = 0L;
/* 120 */     for (int i = 0; i < 8; i++)
/* 121 */       msb = msb << 8 | data[i] & 0xFF;
/* 122 */     for (int i = 8; i < 16; i++)
/* 123 */       lsb = lsb << 8 | data[i] & 0xFF;
/* 124 */     this.mostSigBits = msb;
/* 125 */     this.leastSigBits = lsb;
/*     */   }
/*     */ 
/*     */   public EgovFormBasedUUID(long mostSigBits, long leastSigBits)
/*     */   {
/* 138 */     this.mostSigBits = mostSigBits;
/* 139 */     this.leastSigBits = leastSigBits;
/*     */   }
/*     */ 
/*     */   public static EgovFormBasedUUID randomUUID()
/*     */   {
/* 151 */     SecureRandom ng = numberGenerator;
/* 152 */     if (ng == null) {
/* 153 */       numberGenerator = ng = new SecureRandom();
/*     */     }
/*     */ 
/* 156 */     byte[] randomBytes = new byte[16];
/* 157 */     ng.nextBytes(randomBytes);
/*     */     byte[] tmp33_30 = randomBytes; tmp33_30[6] = ((byte)(tmp33_30[6] & 0xF));
/*     */     byte[] tmp43_40 = randomBytes; tmp43_40[6] = ((byte)(tmp43_40[6] | 0x40));
/*     */     byte[] tmp53_50 = randomBytes; tmp53_50[8] = ((byte)(tmp53_50[8] & 0x3F));
/*     */     byte[] tmp63_60 = randomBytes; tmp63_60[8] = ((byte)(tmp63_60[8] | 0x80));
/*     */ 
/* 163 */     return new EgovFormBasedUUID(randomBytes);
/*     */   }
/*     */ 
/*     */   public static EgovFormBasedUUID nameUUIDFromBytes(byte[] name)
/*     */   {
/*     */     
/*     */    
/* 208 */     return new EgovFormBasedUUID(null);
/*     */   }
/*     */ 
/*     */   public static EgovFormBasedUUID fromString(String name)
/*     */   {
/* 223 */     String[] components = name.split("-");
/* 224 */     if (components.length != 5)
/* 225 */       throw new IllegalArgumentException("Invalid UUID string: " + name);
/* 226 */     for (int i = 0; i < 5; i++) {
/* 227 */       components[i] = ("0x" + components[i]);
/*     */     }
/* 229 */     long mostSigBits = Long.decode(components[0]).longValue();
/* 230 */     mostSigBits <<= 16;
/* 231 */     mostSigBits |= Long.decode(components[1]).longValue();
/* 232 */     mostSigBits <<= 16;
/* 233 */     mostSigBits |= Long.decode(components[2]).longValue();
/*     */ 
/* 235 */     long leastSigBits = Long.decode(components[3]).longValue();
/* 236 */     leastSigBits <<= 48;
/* 237 */     leastSigBits |= Long.decode(components[4]).longValue();
/*     */ 
/* 239 */     return new EgovFormBasedUUID(mostSigBits, leastSigBits);
/*     */   }
/*     */ 
/*     */   public long getLeastSignificantBits()
/*     */   {
/* 250 */     return this.leastSigBits;
/*     */   }
/*     */ 
/*     */   public long getMostSignificantBits()
/*     */   {
/* 259 */     return this.mostSigBits;
/*     */   }
/*     */ 
/*     */   public int version()
/*     */   {
/* 278 */     if (this.version < 0)
/*     */     {
/* 280 */       this.version = ((int)(this.mostSigBits >> 12 & 0xF));
/*     */     }
/* 282 */     return this.version;
/*     */   }
/*     */ 
/*     */   public int variant()
/*     */   {
/* 301 */     if (this.variant < 0)
/*     */     {
/* 303 */       if (this.leastSigBits >>> 63 == 0L)
/* 304 */         this.variant = 0;
/* 305 */       else if (this.leastSigBits >>> 62 == 2L)
/* 306 */         this.variant = 2;
/*     */       else {
/* 308 */         this.variant = ((int)(this.leastSigBits >>> 61));
/*     */       }
/*     */     }
/* 311 */     return this.variant;
/*     */   }
/*     */ 
/*     */   public long timestamp()
/*     */   {
/* 331 */     if (version() != 1) {
/* 332 */       throw new UnsupportedOperationException("Not a time-based UUID");
/*     */     }
/* 334 */     long result = this.timestamp;
/* 335 */     if (result < 0L) {
/* 336 */       result = (this.mostSigBits & 0xFFF) << 48;
/* 337 */       result |= (this.mostSigBits >> 16 & 0xFFFF) << 32;
/* 338 */       result |= this.mostSigBits >>> 32;
/* 339 */       this.timestamp = result;
/*     */     }
/* 341 */     return result;
/*     */   }
/*     */ 
/*     */   public int clockSequence()
/*     */   {
/* 362 */     if (version() != 1) {
/* 363 */       throw new UnsupportedOperationException("Not a time-based UUID");
/*     */     }
/* 365 */     if (this.sequence < 0) {
/* 366 */       this.sequence = ((int)((this.leastSigBits & 0x0) >>> 48));
/*     */     }
/* 368 */     return this.sequence;
/*     */   }
/*     */ 
/*     */   public long node()
/*     */   {
/* 389 */     if (version() != 1) {
/* 390 */       throw new UnsupportedOperationException("Not a time-based UUID");
/*     */     }
/* 392 */     if (this.node < 0L) {
/* 393 */       this.node = (this.leastSigBits & 0xFFFFFFFF);
/*     */     }
/* 395 */     return this.node;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 428 */     return digits(this.mostSigBits >> 32, 8) + "-" + 
/* 429 */       digits(this.mostSigBits >> 16, 4) + "-" + digits(this.mostSigBits, 4) + 
/* 430 */       "-" + digits(this.leastSigBits >> 48, 4) + "-" + digits(
/* 431 */       this.leastSigBits, 12);
/*     */   }
/*     */ 
/*     */   private static String digits(long val, int digits)
/*     */   {
/* 436 */     long hi = 1L << digits * 4;
/* 437 */     return Long.toHexString(hi | val & hi - 1L).substring(1);
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 447 */     if (this.hashCode == -1) {
/* 448 */       this.hashCode = 
/* 449 */         ((int)(this.mostSigBits >> 32 ^ this.mostSigBits ^ 
/* 449 */         this.leastSigBits >> 32 ^ this.leastSigBits));
/*     */     }
/* 451 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 468 */     if (obj == null) {
/* 469 */       return false;
/*     */     }
/* 471 */     if (!(obj instanceof EgovFormBasedUUID))
/* 472 */       return false;
/* 473 */     if (((EgovFormBasedUUID)obj).variant() != variant())
/* 474 */       return false;
/* 475 */     EgovFormBasedUUID id = (EgovFormBasedUUID)obj;
/* 476 */     return (this.mostSigBits == id.mostSigBits) && (this.leastSigBits == id.leastSigBits);
/*     */   }
/*     */ 
/*     */   public int compareTo(EgovFormBasedUUID val)
/*     */   {
/* 497 */     return 
/* 500 */       this.leastSigBits > val.leastSigBits ? 1 : this.leastSigBits < val.leastSigBits ? -1 : this.mostSigBits > val.mostSigBits ? 1 : this.mostSigBits < val.mostSigBits ? -1 : 
/* 500 */       0;
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 511 */     in.defaultReadObject();
/*     */ 
/* 514 */     this.version = -1;
/* 515 */     this.variant = -1;
/* 516 */     this.timestamp = -1L;
/* 517 */     this.sequence = -1;
/* 518 */     this.node = -1L;
/* 519 */     this.hashCode = -1;
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.service.EgovFormBasedUUID
 * JD-Core Version:    0.6.2
 */