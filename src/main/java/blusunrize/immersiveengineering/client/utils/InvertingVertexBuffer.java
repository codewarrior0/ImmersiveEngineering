package blusunrize.immersiveengineering.client.utils;

import com.mojang.blaze3d.vertex.IVertexBuilder;

public class InvertingVertexBuffer extends CollectingVertexBuilder
{
	private final int verticesPerPrimitive;
	private final IVertexBuilder baseBuilder;

	public InvertingVertexBuffer(int verticesPerPrimitive, IVertexBuilder baseBuilder)
	{
		this.verticesPerPrimitive = verticesPerPrimitive;
		this.baseBuilder = baseBuilder;
	}

	@Override
	public void endVertex()
	{
		super.endVertex();
		if(vertices.size() >= verticesPerPrimitive)
		{
			for(int i = vertices.size()-1; i >= 0; --i)
				vertices.get(i).forEach(c -> c.accept(baseBuilder));
			vertices.clear();
		}
	}
}
