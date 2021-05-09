/*
Copyright 2021 Amazon.com, Inc. or its affiliates. All Rights Reserved.
Permission is hereby granted, free of charge, to any person obtaining a copy of this
software and associated documentation files (the "Software"), to deal in the Software
without restriction, including without limitation the rights to use, copy, modify,
merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.example.aws.containerdemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelFuture;

public class App {  
  
    private static final int HTTP_PORT = 8090;  
  
    public void run() throws Exception {  
  
        // Create the multithreaded event loops for the server
        EventLoopGroup bossGroup = new NioEventLoopGroup();  
        EventLoopGroup workerGroup = new NioEventLoopGroup();  
  
        try {  
            // A helper class that simplifies server configuration           
            ServerBootstrap httpBootstrap = new ServerBootstrap();  
            
            // Configure the server
            httpBootstrap.group(bossGroup, workerGroup)  
                .channel(NioServerSocketChannel.class)  
                .childHandler(new HttpServerInitializer()) // <-- Our handler created here  
                .option(ChannelOption.SO_BACKLOG, 128)  
                .childOption(ChannelOption.SO_KEEPALIVE, true);  
  
            // Bind and start to accept incoming connections.  
            ChannelFuture httpChannel = httpBootstrap.bind(HTTP_PORT).sync(); 
            
            // Wait until server socket is closed
            httpChannel.channel().closeFuture().sync();  
        }  
        finally {  
            workerGroup.shutdownGracefully();  
            bossGroup.shutdownGracefully();  
        }  
    }  
  
    public static void main(String[] args) throws Exception {  
        new App().run();  
    }  
      
}