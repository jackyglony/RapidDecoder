package agu.bitmap.png.chunks;

import agu.bitmap.png.ImageInfo;
import agu.bitmap.png.PngHelperInternal;
import agu.bitmap.png.PngjException;

/**
 * gAMA chunk.
 * <p>
 * see http://www.w3.org/TR/PNG/#11gAMA
 */
public class PngChunkGAMA extends PngChunkSingle {
	public final static String ID = ChunkHelper.gAMA;

	// http://www.w3.org/TR/PNG/#11gAMA
	private double gamma;

	public PngChunkGAMA(ImageInfo info) {
		super(ID, info);
	}

	@Override
	public ChunkOrderingConstraint getOrderingConstraint() {
		return ChunkOrderingConstraint.BEFORE_PLTE_AND_IDAT;
	}

	@Override
	public void parseFromRaw(ChunkRaw chunk) {
		if (chunk.len != 4)
			throw new PngjException("bad chunk " + chunk);
		int g = PngHelperInternal.readInt4fromBytes(chunk.data, 0);
		gamma = ((double) g) / 100000.0;
	}

	@Override
	public PngChunk cloneForWrite(ImageInfo imgInfo) {
		PngChunkGAMA other = new PngChunkGAMA(imgInfo);
		other.gamma = gamma;
		return other;
	}

	public double getGamma() {
		return gamma;
	}

	public void setGamma(double gamma) {
		this.gamma = gamma;
	}

}